package aquarius0715.shigenplus

import org.bukkit.Sound
import org.bukkit.entity.Player

class SoundData(var plugin: ShigenPlus) {
    fun soundData(player: Player) {
        for (sound in Sound.values()) {
            if (sound.toString().equals(plugin.config.getString("sound"), ignoreCase = true)) {
                player.playSound(player.location, sound, 1.0f, 8.0f)
            }
        }
    }
}