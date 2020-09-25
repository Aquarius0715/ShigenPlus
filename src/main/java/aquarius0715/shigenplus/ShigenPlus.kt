package aquarius0715.shigenplus

import me.staartvin.statz.Statz
import me.staartvin.statz.api.API
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class ShigenPlus : JavaPlugin() {
    var api: API? = null

    var pluginStats = true

    var prefix: String = "${"" +  ChatColor.WHITE + ChatColor.BOLD}[" +
            "${"" + ChatColor.DARK_GREEN + ChatColor.BOLD}Shigen" +
            "${"" + ChatColor.GREEN + ChatColor.BOLD}Plus" +
            "${"" + ChatColor.WHITE + ChatColor.BOLD}]"

    val scoreboardStats = mutableMapOf<UUID, Boolean>()
    val locationStats = mutableMapOf<UUID, Boolean>()
    val rankStats = mutableMapOf<UUID, Boolean>()
    val nextRankStats = mutableMapOf<UUID, Boolean>()
    val allMinedStats = mutableMapOf<UUID, Boolean>()

    var scoreBoard: ScoreBoard = ScoreBoard(this)

    override fun onEnable() {
        val statz = Bukkit.getServer().pluginManager.getPlugin("Statz")
        Objects.requireNonNull(getCommand("sp"))!!.setExecutor(Commands(this))
        server.pluginManager.registerEvents(Events(this), this)
        if (statz != null && statz.isEnabled) {
            api = API(statz as Statz?)
        }
        scoreBoard.createScoreBoard()
        scoreBoard.updateScoreBoard()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}