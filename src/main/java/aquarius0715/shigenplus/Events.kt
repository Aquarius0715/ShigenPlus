package aquarius0715.shigenplus

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class Events(val plugin: ShigenPlus): Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        val uuid = event.player.uniqueId

        plugin.scoreboardStats.putIfAbsent(uuid, true)
        plugin.locationStats.putIfAbsent(uuid, true)
        plugin.rankStats.putIfAbsent(uuid, true)
        plugin.nextRankStats.putIfAbsent(uuid, true)
        plugin.allMinedStats.putIfAbsent(uuid, true)

    }

}