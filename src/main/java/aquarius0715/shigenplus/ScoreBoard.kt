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

class ScoreBoard(var plugin: ShigenPlus): Thread() {

    var scoreboardManager: ScoreboardManager? = null
    var scoreboard: Scoreboard? = null
    var objective: Objective? = null
    val c: CharArray = plugin.prefix.toCharArray()

    class PlayerData {
        var rank: String? = null
        var remainRank: String? = null
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

                for (player in Bukkit.getOnlinePlayers()) {

                    createScoreBoard()

                    if (!plugin.pluginStats) {

                        player.scoreboard = (Bukkit.getScoreboardManager().newScoreboard)

                        continue

                    }

                    val uuid = player.uniqueId

                    if (!plugin.stats[uuid]!!.scoreboard) {

                        continue

                    }

                    //val space4 = objective!!.getScore("    ")
                    //space4.score = 14

                    if (plugin.stats[uuid]!!.location) {

                        val location = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}X " +
                                "${ChatColor.YELLOW}${player.location.x.toInt()} " +
                                "${ChatColor.GREEN}${ChatColor.BOLD}Y " +
                                "${ChatColor.YELLOW}${player.location.y.toInt()} " +
                                "${ChatColor.GREEN}${ChatColor.BOLD}Z " +
                                "${ChatColor.YELLOW}${player.location.z.toInt()}")
                        location.score = 13

                    }

                    if (plugin.stats[uuid]!!.rank) {

                        if (plugin.stats[uuid]!!.space) {

                            val space = objective!!.getScore(" ")
                            space.score = 12

                        }

                        val rank = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}ランク   " +
                                "${ChatColor.WHITE}${ChatColor.BOLD}: " +
                                "${ChatColor.YELLOW}${ChatColor.BOLD}${rankData(player).rank}")
                        rank.score = 11

                    }

                    if (plugin.stats[uuid]!!.nextRank) {

                        if (plugin.stats[uuid]!!.space) {

                            val space6 = objective!!.getScore("      ")
                            space6.score = 10

                        }


                        if (getAllMinedBlocks(player) < 10000000.0) {

                            val remainRank = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}次まで   " +
                                    "${ChatColor.WHITE}${ChatColor.BOLD}: " +
                                    "${ChatColor.YELLOW}${ChatColor.BOLD}${rankData(player).remainRank?.toInt()}")
                            remainRank.score = 9

                        } else {

                            val remainRank = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}32bitまで" +
                                    "${ChatColor.WHITE}${ChatColor.BOLD}: " +
                                    "${ChatColor.YELLOW}${ChatColor.BOLD}${rankData(player).remainRank?.toInt()}")
                            remainRank.score = 9

                        }

                    }

                    if (plugin.stats[uuid]!!.allMined) {

                        if (plugin.stats[uuid]!!.space) {

                            val space3 = objective!!.getScore("   ")
                            space3.score = 8

                        }

                        val allBroken = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}総採掘量 " +
                                "${ChatColor.WHITE}${ChatColor.BOLD}: " +
                                "${ChatColor.YELLOW}${ChatColor.BOLD}${getAllMinedBlocks(player)}")
                        allBroken.score = 7
                    }

                    if (plugin.stats[uuid]!!.noticeDisplay) {

                        if (plugin.stats[uuid]!!.space) {

                            val space5 = objective!!.getScore("     ")
                            space5.score = 6

                        }

                        if (plugin.stats[uuid]!!.notice) {

                            val notice = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}破壊警告 " +
                                    "${ChatColor.WHITE}${ChatColor.BOLD}: " +
                                    "${ChatColor.GREEN}${ChatColor.BOLD}有効")
                            notice.score = 5

                        } else {

                            val notice = objective!!.getScore("${ChatColor.GREEN}${ChatColor.BOLD}破壊警告 " +
                                    "${ChatColor.WHITE}${ChatColor.BOLD}: " +
                                    "${ChatColor.RED}${ChatColor.BOLD}無効")
                            notice.score = 5

                        }
                    }

                    player.scoreboard = scoreboard!!

                }
            }
        }.runTaskTimer(plugin,0, 10)
    }

    fun getAllMinedBlocks(player: Player): Int {

        return plugin.api?.getTotalOf(PlayerStat.BLOCKS_BROKEN, UUID.fromString(player.uniqueId.toString()), null)!!.toInt()

    }

    fun rankData(player: Player): PlayerData {
        val playerData = PlayerData()

            when(getAllMinedBlocks(player)) {
                in 0.0..1000.0 -> {playerData.rank = "${ChatColor.YELLOW}Tourist"
                    playerData.remainRank = (1000 - getAllMinedBlocks(player)).toString()}

                in 1000.0..10000.0 -> {playerData.rank = "${ChatColor.DARK_GREEN}Crafter"
                    playerData.remainRank = (10000 - getAllMinedBlocks(player)).toString()}

                in 10000.0..30000.0 -> {playerData.rank = "${ChatColor.DARK_GREEN}Crafter${ChatColor.DARK_PURPLE}+"
                    playerData.remainRank = (30000 - getAllMinedBlocks(player)).toString()}

                in 30000.0..50000.0 -> {playerData.rank = "${ChatColor.LIGHT_PURPLE}Expert"
                    playerData.remainRank = (50000 - getAllMinedBlocks(player)).toString()}

                in 50000.0..100000.0 -> {playerData.rank = "${ChatColor.LIGHT_PURPLE}Expert${ChatColor.YELLOW}+"
                    playerData.remainRank = (100000 - getAllMinedBlocks(player)).toString()}

                in 100000.0..200000.0 -> {playerData.rank = "${ChatColor.GRAY}Miner"
                    playerData.remainRank = (200000 - getAllMinedBlocks(player)).toString()}

                in 200000.0..300000.0 -> {playerData.rank = "${ChatColor.GRAY}Miner${ChatColor.YELLOW}+"
                    playerData.remainRank = (300000 - getAllMinedBlocks(player)).toString()}

                in 300000.0..500000.0 -> {playerData.rank = "${ChatColor.WHITE}Super${ChatColor.GRAY}Miner"
                    playerData.remainRank = (500000 - getAllMinedBlocks(player)).toString()}

                in 500000.0..1000000.0 -> {playerData.rank = "${ChatColor.WHITE}Super${ChatColor.GRAY}Miner${ChatColor.YELLOW}+"
                    playerData.remainRank = (1000000 - getAllMinedBlocks(player)).toString()}

                in 1000000.0..3000000.0 -> {playerData.rank = "${ChatColor.DARK_GRAY}${ChatColor.BOLD}Breaker"
                    playerData.remainRank = (3000000 - getAllMinedBlocks(player)).toString()}

                in 3000000.0..5000000.0 -> {playerData.rank = "${ChatColor.DARK_GRAY}${ChatColor.BOLD}Breaker${ChatColor.LIGHT_PURPLE}+"
                    playerData.remainRank = (5000000 - getAllMinedBlocks(player)).toString()}

                in 5000000.0..10000000.0 -> {playerData.rank = "${ChatColor.BLACK}${ChatColor.BOLD}Destroyer"
                    playerData.remainRank = (10000000 - getAllMinedBlocks(player)).toString()}

                in 10000000.0..2147483647.0 -> {playerData.rank = "${ChatColor.BLACK}${ChatColor.BOLD}${ChatColor.MAGIC}" +
                        "a${ChatColor.BLACK}${ChatColor.BOLD}" +
                        "Destroyer${ChatColor.BLACK}${ChatColor.BOLD}${ChatColor.MAGIC}a"
                    playerData.remainRank = (2147483647 - getAllMinedBlocks(player)).toString()}
            }
        return playerData
    }
}
