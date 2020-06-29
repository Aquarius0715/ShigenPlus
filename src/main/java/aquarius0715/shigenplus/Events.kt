package aquarius0715.shigenplus

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemDamageEvent

class Events(var plugin: ShigenPlus) : Listener {
    @EventHandler
    fun onItemDamage(event: PlayerItemDamageEvent) {

    }
}