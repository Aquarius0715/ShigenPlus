package aquarius0715.shigenplus

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemDamageEvent

class Events(var plugin: ShigenPlus) : Listener {

    @EventHandler
    fun onItemDamage(event: PlayerItemDamageEvent) {

        val component = TextComponent()

        val player = event.player

        val maxDurability = event.item.type.maxDurability
        val nowDurability = event.item.type.maxDurability - event.item.durability - 1

        if (!plugin.pluginStats) {
            return
        }


        if (nowDurability <= maxDurability * 0.2 && nowDurability >= maxDurability * 0.05) {
            component.text = "${ChatColor.GRAY}${ChatColor.BOLD}" +
                    "(ツール名: ${toolData(event)}" +
                    " /最大耐久値: $maxDurability" +
                    " /${ChatColor.YELLOW}${ChatColor.BOLD}" + "現在の耐久値: $nowDurability${ChatColor.GRAY}${ChatColor.BOLD})"
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component)
        } else
            if (nowDurability <= maxDurability * 0.05) {
                component.text = ("${ChatColor.GRAY}${ChatColor.BOLD}" +
                        "(ツール名: ${toolData(event)}" +
                        " /最大耐久値: $maxDurability" +
                        " /${ChatColor.DARK_RED}${ChatColor.BOLD}" + "現在の耐久値: $nowDurability${ChatColor.GRAY}${ChatColor.BOLD})")
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component)
                plugin.SoundData.soundData(event.player)
            }
    }
}

fun toolData(event: PlayerItemDamageEvent): String {

    return when(event.item.type) {
        Material.WOODEN_AXE -> "木の斧"
        Material.WOODEN_HOE -> "木のクワ"
        Material.WOODEN_PICKAXE -> "木のツルハシ"
        Material.WOODEN_SHOVEL -> "木のシャベル"
        Material.WOODEN_SWORD -> "木の剣"

        Material.STONE_AXE -> "石の斧"
        Material.STONE_HOE -> "石のクワ"
        Material.STONE_PICKAXE -> "石のツルハシ"
        Material.STONE_SHOVEL -> "石のシャベル"
        Material.STONE_SWORD -> "石の剣"

        Material.IRON_AXE -> "木の斧"
        Material.IRON_HOE -> "木のクワ"
        Material.IRON_PICKAXE -> "木のツルハシ"
        Material.IRON_SHOVEL -> "木のシャベル"
        Material.IRON_SWORD -> "木の剣"

        Material.DIAMOND_AXE -> "木の斧"
        Material.DIAMOND_HOE -> "木のクワ"
        Material.DIAMOND_PICKAXE -> "木のツルハシ"
        Material.DIAMOND_SHOVEL -> "木のシャベル"
        Material.DIAMOND_SWORD -> "木の剣"

        Material.GOLDEN_AXE -> "木の斧"
        Material.GOLDEN_HOE -> "木のクワ"
        Material.GOLDEN_PICKAXE -> "木のツルハシ"
        Material.GOLDEN_SHOVEL -> "木のシャベル"
        Material.GOLDEN_SWORD -> "木の剣"

        Material.FLINT_AND_STEEL -> "火打ち石と打ち金"

        Material.FISHING_ROD -> "釣竿"

        Material.SHEARS -> "ハサミ"

        else -> ""
    }
}
