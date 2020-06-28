package aquarius0715.shigenplus

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Commands(var plugin: ShigenPlus) : CommandExecutor {

    var nowDate: Long = 0
    var cmdDate: Long = 0

    var cmdStats: Boolean = false

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (label.equals("sp", ignoreCase = true)) {
            if (sender !is Player) {
                sender.sendMessage("You cannot this")
                return true
            }

            when(args.size) {
                0 -> {
                    sender.sendMessage("${plugin.prefix}/sp helpと入力してコマンドを確認してください。")
                    return true
                }

                1 -> {
                    when (args[0]) {
                        "help" -> {
                            sender.sendMessage("==========================${plugin.prefix}==========================")
                            sender.sendMessage("${plugin.prefix}</sp help> : この説明画面を開きます。)")
                            sender.sendMessage("${plugin.prefix}</sp scoreboard> : スコアボードの表示非表示を切り替えます。")
                            if (sender.hasPermission("admin")) {
                                sender.sendMessage("${plugin.prefix}</sp stats> : このプラグインを有効無効を切り替えます。")
                            }
                            sender.sendMessage("==========================${plugin.prefix}==========================")
                            return true
                        }

                        "scoreboard" -> {
                            plugin.scoreboardStats.putIfAbsent(sender.uniqueId, true)

                             if (plugin.scoreboardStats[sender.uniqueId]!!) {
                                sender.sendMessage("${plugin.prefix}スコアボードを${"" + ChatColor.RED}非表示にしました。")
                                plugin.scoreboardStats[sender.uniqueId] = false
                                return true
                            } else {
                                sender.sendMessage("${plugin.prefix}スコアボードを${"" + ChatColor.GREEN}表示にしました。")
                                plugin.scoreboardStats[sender.uniqueId] = true
                                return true
                            }
                        }

                        "stats" -> {

                            if (!cmdStats) {
                                sender.sendMessage("" +
                                        "${plugin.prefix}プラグインは" + "" +
                                        "${if (plugin.pluginStats) "" + ChatColor.GREEN + ChatColor.BOLD + "有効"
                                        else "" + ChatColor.RED + ChatColor.BOLD + "無効"} " +
                                        "です。切り替えたい場合は3秒以内にもう一度このコマンドを入力してください。")
                                cmdStats = true
                                cmdDate = Date().time
                                return true
                            } else {
                                nowDate = Date().time
                            }

                            if (nowDate - cmdDate < 3000) {
                                if (sender.hasPermission("admin") && plugin.pluginStats) {
                                    plugin.pluginStats = false
                                    sender.sendMessage("" +
                                            "${plugin.prefix}プラグインを" +
                                            "${"" + ChatColor.RED + ChatColor.BOLD}無効" +
                                            "${"" + ChatColor.WHITE + ChatColor.BOLD}にしました。")
                                    cmdStats = false
                                    return true
                                } else
                                    plugin.pluginStats = true
                                sender.sendMessage("" +
                                        "${plugin.prefix}プラグインを" +
                                        "${"" + ChatColor.GREEN + ChatColor.BOLD}有効" +
                                        "${"" + ChatColor.WHITE + ChatColor.BOLD}にしました。")
                                cmdStats = false
                                return true
                            } else {
                                cmdStats = false
                            }
                        }
                    }
                }
            }
        }
        return false
    }
}