package aquarius0715.shigenplus

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class GUI(private val plugin: ShigenPlus): Listener {

    fun openSettingInventory(player: Player) {

        val settingInv = Bukkit.createInventory(null, 9, "${"" +  ChatColor.WHITE + ChatColor.BOLD}[" +
                "${"" + ChatColor.DARK_GREEN + ChatColor.BOLD}Shigen" +
                "${"" + ChatColor.GREEN + ChatColor.BOLD}Plus" +
                "${"" + ChatColor.WHITE + ChatColor.BOLD}]")

        val uuid = player.uniqueId
        var button: ItemStack
        var buttonMeta: ItemMeta
        val lore: ArrayList<String> = arrayListOf()
        lore.add("${ChatColor.DARK_GRAY}ここをクリックで表示切り替え")

        if (plugin.stats[uuid]!!.scoreboard) {
            
            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}スコアボード: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta
            
            settingInv.setItem(0, button)
            
        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}スコアボード: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(0, button)
            
        }
        
        if (plugin.stats[uuid]!!.location) {

            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}座標: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(1, button)
            
        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}座標: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(1, button)

        }

        if (plugin.stats[uuid]!!.rank) {

            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}ランク: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(2, button)

        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}ランク: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(2, button)

        }

        if (plugin.stats[uuid]!!.nextRank) {

            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}次のランク: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(3, button)

        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}次のランク: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(3, button)

        }

        if (plugin.stats[uuid]!!.allMined) {

            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}総採掘量: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(4, button)

        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}総採掘量: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(4, button)

        }

        if (plugin.stats[uuid]!!.noticeDisplay) {

            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}破壊警告表示: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(5, button)

        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}破壊警告表示: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(5, button)

        }

        if (plugin.stats[uuid]!!.notice) {

            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}破壊警告: ${ChatColor.GREEN}${ChatColor.BOLD}有効")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(6, button)

        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}破壊警告: ${ChatColor.RED}${ChatColor.BOLD}無効")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(6, button)

        }

        if (plugin.stats[uuid]!!.space) {

            button = ItemStack(Material.EMERALD_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}空白: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(7, button)

        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)
            buttonMeta = button.itemMeta

            buttonMeta.setDisplayName("${ChatColor.BOLD}空白: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(7, button)

        }

        player.openInventory(settingInv)

    }

    @EventHandler
    fun clickSettingInv(event: InventoryClickEvent) {

        val player = event.whoClicked as Player

        if (event.view.title != plugin.prefix) return
        if (event.inventory == player.inventory) return
        if (event.currentItem == null) return
        if (event.currentItem!!.itemMeta == null) return

        event.isCancelled = true

        val uuid = player.uniqueId

        when (event.slot) {

            0 -> {

                plugin.stats[uuid]!!.scoreboard = !plugin.stats[uuid]!!.scoreboard
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)
                player.scoreboard = (Bukkit.getScoreboardManager().newScoreboard)

            }

            1 -> {

                plugin.stats[uuid]!!.location = !plugin.stats[uuid]!!.location
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)

            }

            2 -> {

                plugin.stats[uuid]!!.rank = !plugin.stats[uuid]!!.rank
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)

            }

            3 -> {

                plugin.stats[uuid]!!.nextRank = !plugin.stats[uuid]!!.nextRank
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)

            }

            4 -> {

                plugin.stats[uuid]!!.allMined = !plugin.stats[uuid]!!.allMined
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)

            }

            5 -> {

                plugin.stats[uuid]!!.noticeDisplay = !plugin.stats[uuid]!!.noticeDisplay
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)

            }

            6 -> {

                plugin.stats[uuid]!!.notice = !plugin.stats[uuid]!!.notice
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)

            }

            7 -> {

                plugin.stats[uuid]!!.space = !plugin.stats[uuid]!!.space
                openSettingInventory(player)
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 8.0F, -1.0F)

            }

        }

    }

}