package n3wb13.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ServerUtil {

    public static void log(Plugin plugin, Object log) {
        ConsoleCommandSender console = plugin.getServer().getConsoleSender();
        console.sendMessage("[" + plugin.getName() + "] " + log.toString().replaceAll("&([0-9a-fk-or])", "\u00a7$1"));
    }

    public static void log(Plugin plugin, Object log, Object playerObj) {
        if (playerObj instanceof Player) {
            Player player = (Player) playerObj;
            player.sendMessage(log.toString());
        } else log(plugin, log);
    }

    public static void errorLog(Plugin plugin, Object log) {
        errorLog(plugin, log, false);
    }

    public static void errorLog(Plugin plugin, Object log, boolean shutdown) {
        ConsoleCommandSender console = plugin.getServer().getConsoleSender();
        console.sendMessage("[" + plugin.getName() + "] " + log.toString().replaceAll("&([0-9a-fk-or])", "\u00a7$1"));
        if (shutdown) Bukkit.getServer().shutdown();
    }
}
