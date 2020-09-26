package aquarius0715.shigenplus

import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Commands(var plugin: ShigenPlus) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (label.equals("sp", ignoreCase = true)) {
            if (sender !is Player) {
                sender.sendMessage("You cannot this")
                return true
            }

            when(args.size) {
                0 -> {

                    if (!plugin.pluginStats) {

                        sender.sendMessage("${plugin.prefix}このプラグインは現在オフになっています。")

                    }

                    plugin.gui.openSettingInventory(sender)
                    sender.playSound(sender.location, Sound.BLOCK_CHEST_OPEN, 8.0F, 2.0F)

                    return true
                }

                1 -> {
                    when (args[0]) {
                        "help" -> {
                            sender.sendMessage("=======================${plugin.prefix}=======================")
                            sender.sendMessage("${plugin.prefix}</sp> : スコアボードの表示設定を開きます。")
                            sender.sendMessage("${plugin.prefix}</sp help> : この説明画面を開きます。)")
                            sender.sendMessage("${plugin.prefix}</sp on> : プラグインをオンにします。")
                            sender.sendMessage("${plugin.prefix}</sp off> : プラグインをオフにします。")
                            sender.sendMessage("${plugin.prefix}Created By Aquarius0715")
                            sender.sendMessage("${plugin.prefix}Ver 1.1.5 Released on 2020/09/26")
                            sender.sendMessage("=======================${plugin.prefix}=======================")
                            return true
                        }

                        "on" -> {

                            if (plugin.pluginStats) {

                                sender.sendMessage("${plugin.prefix}このプラグインはすでにオンになっています。")

                                return true

                            } else {

                                plugin.pluginStats = true

                                sender.sendMessage("${plugin.prefix}プラグインをオンにしました。")

                                return true

                            }

                        }

                        "off" -> {

                            if (!plugin.pluginStats) {

                                sender.sendMessage("${plugin.prefix}このプラグインはすでにオフになっています。")

                                return true

                            } else {

                                plugin.pluginStats = false

                                sender.sendMessage("${plugin.prefix}プラグインをオフにしました。")

                                return true

                            }

                        }

                    }

                }

            }

        }

        return false
    }
}