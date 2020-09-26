package aquarius0715.shigenplus

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class Events(val plugin: ShigenPlus): Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        val uuid = event.player.uniqueId

        plugin.stats.putIfAbsent(uuid, ShigenPlus.Stats(scoreboard = true,
                location = false,
                rank = true,
                nextRank = true,
                allMined = true,
                noticeDisplay = false,
                notice = true,
                space = true))

        event.player.sendMessage("${plugin.prefix}/sp help でコマンドを確認できます！")
    }

    @EventHandler
    fun onItemDamage(event: PlayerItemDamageEvent) {

        val player = event.player

        if (!plugin.pluginStats) return
        if (!plugin.stats[player.uniqueId]?.notice!!) return

        val maxDurability: Int = event.item.type.maxDurability.toInt()
        val nowDurability: Int = event.item.type.maxDurability - event.item.durability.toInt() - 1

        if (nowDurability > maxDurability * 0.2) return

        if (nowDurability <= maxDurability * 0.2 && nowDurability >= maxDurability * 0.05) {

            val component = TextComponent()

            component.text = "${ChatColor.GRAY}${ChatColor.BOLD}(ツール名: " +
                    event.item.i18NDisplayName +
                    " / 最大耐久値: $maxDurability" +
                    " / ${ChatColor.YELLOW}${ChatColor.BOLD}現在の耐久値: $nowDurability" +
                    "${ChatColor.GRAY}${ChatColor.BOLD})"
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component)

        } else if (nowDurability <= maxDurability * 0.05) {

            val component = TextComponent()

            component.text = "${ChatColor.GRAY}${ChatColor.BOLD}(ツール名: " +
                    event.item.i18NDisplayName +
                    " / 最大耐久値: $maxDurability" +
                    " / ${ChatColor.DARK_RED}${ChatColor.BOLD}現在の耐久値: $nowDurability" +
                    "${ChatColor.GRAY}${ChatColor.BOLD})"
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component)

            player.playSound(player.location, Sound.ENTITY_ARROW_HIT_PLAYER, 8.0F, 0.0F)

        }

    }

    fun getItemName(itemStack: ItemStack): String {

        if (itemStack.itemMeta.hasDisplayName()) {

            return itemStack.itemMeta.displayName

        } else {

            return when (itemStack.type) {

                Material.IRON_SHOVEL -> "鉄のシャベル"
                Material.IRON_PICKAXE -> "鉄のツルハシ"
                Material.IRON_AXE -> "鉄の斧"
                Material.FLINT_AND_STEEL -> "火打石と打ち金"
                Material.WOODEN_SHOVEL -> "木のシャベル"
                Material.WOODEN_PICKAXE -> "木のツルハシ"
                Material.WOODEN_AXE -> "木の斧"
                Material.STONE_SHOVEL -> "石のシャベル"
                else -> "UNKNOWN"

            }

        }

    }

}