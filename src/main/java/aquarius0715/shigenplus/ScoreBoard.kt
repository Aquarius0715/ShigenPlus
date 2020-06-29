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

    class PlayerData {
        var rank: String? = null
        var remainRank: Int = 0
        var allBroken: Int = 0
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

                if (!plugin.pluginStats) {
                    for (player in Bukkit.getOnlinePlayers()) {
                        player.scoreboard = (Bukkit.getScoreboardManager()!!.newScoreboard)
                    }
                    return
                }

                for (player in Bukkit.getOnlinePlayers()) {

                    createScoreBoard()

                    plugin.scoreboardStats.putIfAbsent(player.uniqueId, true)

                    if (!plugin.scoreboardStats[player.uniqueId]!!) {
                        player.scoreboard = (Bukkit.getScoreboardManager()!!.newScoreboard)
                        return
                    }

                    val space = objective!!.getScore(" ")
                    space.score = 12

                    val rank = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}ランク " +
                            "${ChatColor.YELLOW}${ChatColor.BOLD}: ${rankData(player).rank}")
                    rank.score = 11

                    val space2 = objective!!.getScore("  ")
                    space2.score = 10

                    val allBroken = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}総採掘量 " +
                            "${ChatColor.YELLOW}${ChatColor.BOLD}: ${ChatColor.WHITE}${ChatColor.BOLD}${rankData(player).allBroken} ブロック")
                    allBroken.score = 9

                    val space3 = objective!!.getScore("   ")
                    space3.score = 8

                    if (rankData(player).allBroken > 10_000_000) {
                        val remainRank = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}int最大値まで "+
                                "${ChatColor.YELLOW}${ChatColor.BOLD}: ${ChatColor.WHITE}${ChatColor.BOLD}${rankData(player).remainRank} ブロック")
                        remainRank.score = 7
                    } else {
                        val remainRank = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}次のランクまで " +
                                "${ChatColor.YELLOW}${ChatColor.BOLD}: ${ChatColor.WHITE}${ChatColor.BOLD}${rankData(player).remainRank} ブロック")
                        remainRank.score = 7
                    }

                    player.scoreboard = scoreboard!!
                }
            }
        }.runTaskTimer(plugin,0, 20)
    }

    fun rankData(player: Player): PlayerData {
        val playerData = PlayerData()

        playerData.allBroken = plugin.api?.getTotalOf(PlayerStat.BLOCKS_BROKEN, UUID.fromString(player.uniqueId.toString()), null)!!.toInt()

        when(playerData.allBroken) {
            in 0..1_000 -> {playerData.rank = "${ChatColor.YELLOW}Tourist"
                playerData.remainRank = (1_000 - playerData.allBroken).toString().toInt()}

            in 1_000..10_000 -> {playerData.rank = "${ChatColor.DARK_GREEN}Crafter"
                playerData.remainRank = (10_000 - playerData.allBroken).toString().toInt()}

            in 10_000..30_000 -> {playerData.rank = "${ChatColor.DARK_GREEN}Crafter${ChatColor.DARK_PURPLE}+"
                playerData.remainRank = (30_000 - playerData.allBroken).toString().toInt()}

            in 30_000..50_000 -> {playerData.rank = "${ChatColor.LIGHT_PURPLE}Expert"
                playerData.remainRank = (50_000 - playerData.allBroken).toString().toInt()}

            in 50_000..100_000 -> {playerData.rank = "${ChatColor.LIGHT_PURPLE}Expert${ChatColor.YELLOW}+"
                playerData.remainRank = (100_000 - playerData.allBroken).toString().toInt()}

            in 100_000..200_000 -> {playerData.rank = "${ChatColor.GRAY}Miner"
                playerData.remainRank = (200_000 - playerData.allBroken).toString().toInt()}

            in 200_000..300_000 -> {playerData.rank = "${ChatColor.GRAY}Miner${ChatColor.YELLOW}+"
                playerData.remainRank = (300_000 - playerData.allBroken).toString().toInt()}

            in 300_000..500_000 -> {playerData.rank = "${ChatColor.WHITE}Super${ChatColor.GRAY}Miner"
                playerData.remainRank = (500_000 - playerData.allBroken).toString().toInt()}

            in 500_000..1_000_000 -> {playerData.rank = "${ChatColor.WHITE}Super${ChatColor.GRAY}Miner${ChatColor.YELLOW}+"
                playerData.remainRank = (1_000_000 - playerData.allBroken).toString().toInt()}

            in 1_000_000..3_000_000 -> {playerData.rank = "${ChatColor.DARK_GRAY}${ChatColor.BOLD}Breaker"
                playerData.remainRank = (3_000_000 - playerData.allBroken).toString().toInt()}

            in 3_000_000..5_000_000 -> {playerData.rank = "${ChatColor.DARK_GRAY}${ChatColor.BOLD}Breaker${ChatColor.LIGHT_PURPLE}+"
                playerData.remainRank = (5_000_000 - playerData.allBroken).toString().toInt()}

            in 5_000_000..10_000_000 -> {playerData.rank = "${ChatColor.BLACK}${ChatColor.BOLD}Destroyer"
                playerData.remainRank = (10_000_000 - playerData.allBroken).toString().toInt()}

            in 10_000_000..2_147_483_647 -> {playerData.rank = "${ChatColor.BLACK}${ChatColor.BOLD}${ChatColor.MAGIC}" +
                    "a${ChatColor.BLACK}${ChatColor.BOLD}" +
                    "Destroyer${ChatColor.BLACK}${ChatColor.BOLD}${ChatColor.MAGIC}a"
                playerData.remainRank = (2_147_483_647 - playerData.allBroken).toString().toInt()}
        }
        return playerData
    }
}
