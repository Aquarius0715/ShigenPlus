package aquarius0715.shigenplus

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class GUI(val plugin: ShigenPlus) {

    fun openSettingInventory(player: Player) {

        val settingInv = Bukkit.createInventory(null, 9, "${"" +  ChatColor.WHITE + ChatColor.BOLD}[" +
                "${"" + ChatColor.DARK_GREEN + ChatColor.BOLD}Shigen" +
                "${"" + ChatColor.GREEN + ChatColor.BOLD}Plus" +
                "${"" + ChatColor.WHITE + ChatColor.BOLD}]")

        val uuid = player.uniqueId
        
        var button = ItemStack(Material.AIR)
        val buttonMeta = button.itemMeta
        val lore: ArrayList<String> = arrayListOf()

        if (plugin.scoreboardStats[uuid]!!) {
            
            button = ItemStack(Material.EMERALD_BLOCK)
            
            buttonMeta.setDisplayName("${ChatColor.BOLD}スコアボード: ${ChatColor.GREEN}${ChatColor.BOLD}表示")
            lore.add("${ChatColor.DARK_GRAY}ここをクリックで表示切り替え")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta
            
            settingInv.setItem(0, button)
            
        } else {

            button = ItemStack(Material.REDSTONE_BLOCK)

            buttonMeta.setDisplayName("${ChatColor.BOLD}スコアボード: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            lore.add("${ChatColor.DARK_GRAY}ここをクリックで表示切り替え")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(0, button)
            
        }
        
        if (plugin.locationStats[uuid]!!) {

            button = ItemStack(Material.EMERALD_BLOCK)

            buttonMeta.setDisplayName("${ChatColor.BOLD}ス: ${ChatColor.RED}${ChatColor.BOLD}非表示")
            lore.add("${ChatColor.DARK_GRAY}ここをクリックで表示切り替え")
            buttonMeta.lore = lore
            button.itemMeta = buttonMeta

            settingInv.setItem(0, button)
            
        }

    }

}