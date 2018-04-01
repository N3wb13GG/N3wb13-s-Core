package n3wb13.core;

import n3wb13.core.managers.Managers;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    public Managers managers;

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }

    public void registerPlugin(Plugin plugin) {
        managers.setup(plugin);
    }
}
