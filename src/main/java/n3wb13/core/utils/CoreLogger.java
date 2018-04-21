package n3wb13.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public final class CoreLogger {

    public static String getNameFormat(Plugin plugin) {
        return "[" + plugin.getName() + "] ";
    }

    public static void log(Object message) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(message.toString().replaceAll("&([0-9a-fk-or])", "\u00a7$1"));
    }

    public static void log(Object message, Object playerObj) {
        if (playerObj instanceof Player) {
            Player player = (Player) playerObj;
            player.sendMessage(message.toString());
        } else log(message);
    }

    public static void errorLog(Object message) {
        errorLog(message, false);
    }

    public static void errorLog(Object message, boolean shutdown) {
        log(message);
        if (shutdown) Bukkit.getServer().shutdown();
    }
}
