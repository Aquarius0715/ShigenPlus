package aquarius0715.shigenplus

import me.staartvin.statz.datamanager.player.PlayerStat
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.ScoreboardManager
import java.util.*

class ScoreBoard(var plugin: ShigenPlus) {

    var scoreboardManager: ScoreboardManager? = null
    var scoreboard: Scoreboard? = null
    var objective: Objective? = null

    class playerData {
        var rank: String? = null
        var remainRank: String? = null
        var allBroken: Double = 0.0
    }

    fun createScoreBoard() {
        scoreboardManager = Bukkit.getScoreboardManager()
        scoreboard = Objects.requireNonNull(scoreboardManager)!!.newScoreboard
        objective = scoreboard!!.registerNewObjective("shigenPlus", "Dummy")
        objective!!.displaySlot = DisplaySlot.SIDEBAR
        objective!!.displayName = plugin.prefix
    }

    fun updateScoreBoard() {

        object : BukkitRunnable() {
            override fun run() {

                if (!plugin.pluginStats) cancel()

                for (player in Bukkit.getOnlinePlayers()) {

                    if (!plugin.scoreboardStats[player.uniqueId]!!) {
                        continue
                    }

                    if (plugin.locationStats[player.uniqueId]!!) {

                        val space4 = objective!!.getScore("    ")
                        space4.score = 14

                        val location = objective!!.getScore("座標   : X:${player.location.x} Y:${player.location.y} Z:${player.location.z}")
                        location.score = 13

                    }

                    if (plugin.rankStats[player.uniqueId]!!) {

                        val space = objective!!.getScore(" ")
                        space.score = 12

                        val rank = objective!!.getScore("${ChatColor.GREEN}ランク   : ${rankData(player).rank}")
                        rank.score = 11

                    }

                    if (plugin.nextRankStats[player.uniqueId]!!) {

                        val space2 = objective!!.getScore("  ")
                        space2.score = 10

                        if (rankData(player).allBroken < 10000000.0) {

                            val remainRank = objective!!.getScore("${ChatColor.GREEN}次まで   : ${rankData(player).remainRank}")
                            remainRank.score = 9

                        } else {

                            val remainRank = objective!!.getScore("${ChatColor.GREEN}32bitまで: ${rankData(player).remainRank}")
                            remainRank.score = 9

                        }

                    }

                    if (plugin.allMinedStats[player.uniqueId]!!) {

                        val space3 = objective!!.getScore("   ")
                        space3.score = 8

                        val allBroken = objective!!.getScore("${ChatColor.GREEN}総採掘量 : ${rankData(player).allBroken}")
                        allBroken.score = 7

                    }

                }
            }
        }.runTaskTimer(plugin,0, 20)
    }

    fun rankData(player: Player): playerData {
        val playerData = playerData()

        playerData.allBroken = plugin.api?.getTotalOf(PlayerStat.BLOCKS_BROKEN, UUID.fromString(player.uniqueId.toString()), null)!!

            when(playerData.allBroken) {
                in 0.0..1000.0 -> {playerData.rank = "${ChatColor.YELLOW}Tourist"
                    playerData.remainRank = (1000 - playerData.allBroken).toString()}

                in 1000.0..10000.0 -> {playerData.rank = "${ChatColor.DARK_GREEN}Crafter"
                    playerData.remainRank = (10000 - playerData.allBroken).toString()}

                in 10000.0..30000.0 -> {playerData.rank = "${ChatColor.DARK_GREEN}Crafter${ChatColor.DARK_PURPLE}+"
                    playerData.remainRank = (30000 - playerData.allBroken).toString()}

                in 30000.0..50000.0 -> {playerData.rank = "${ChatColor.LIGHT_PURPLE}Expert"
                    playerData.remainRank = (50000 - playerData.allBroken).toString()}

                in 50000.0..100000.0 -> {playerData.rank = "${ChatColor.LIGHT_PURPLE}Expert${ChatColor.YELLOW}+"
                    playerData.remainRank = (100000 - playerData.allBroken).toString()}

                in 100000.0..200000.0 -> {playerData.rank = "${ChatColor.GRAY}Miner"
                    playerData.remainRank = (200000 - playerData.allBroken).toString()}

                in 200000.0..300000.0 -> {playerData.rank = "${ChatColor.GRAY}Miner${ChatColor.YELLOW}+"
                    playerData.remainRank = (300000 - playerData.allBroken).toString()}

                in 300000.0..500000.0 -> {playerData.rank = "${ChatColor.WHITE}Super${ChatColor.GRAY}Miner"
                    playerData.remainRank = (500000 - playerData.allBroken).toString()}

                in 500000.0..1000000.0 -> {playerData.rank = "${ChatColor.WHITE}Super${ChatColor.GRAY}Miner${ChatColor.YELLOW}+"
                    playerData.remainRank = (1000000 - playerData.allBroken).toString()}

                in 1000000.0..3000000.0 -> {playerData.rank = "${ChatColor.DARK_GRAY}${ChatColor.BOLD}Breaker"
                    playerData.remainRank = (3000000 - playerData.allBroken).toString()}

                in 3000000.0..5000000.0 -> {playerData.rank = "${ChatColor.DARK_GRAY}${ChatColor.BOLD}Breaker${ChatColor.LIGHT_PURPLE}+"
                    playerData.remainRank = (5000000 - playerData.allBroken).toString()}

                in 5000000.0..10000000.0 -> {playerData.rank = "${ChatColor.BLACK}${ChatColor.BOLD}Destroyer"
                    playerData.remainRank = (10000000 - playerData.allBroken).toString()}

                in 10000000.0..2147483647.0 -> {playerData.rank = "${ChatColor.BLACK}${ChatColor.BOLD}${ChatColor.MAGIC}" +
                        "a${ChatColor.BLACK}${ChatColor.BOLD}" +
                        "Destroyer${ChatColor.BLACK}${ChatColor.BOLD}${ChatColor.MAGIC}a"
                    playerData.remainRank = (2147483647 - playerData.allBroken).toString()}
            }
        return playerData
    }
}
