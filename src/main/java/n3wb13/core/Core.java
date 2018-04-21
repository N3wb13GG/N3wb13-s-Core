package n3wb13.core;

import n3wb13.core.managers.MyManagers;
import n3wb13.core.managers.listeners.ListenerManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Core extends JavaPlugin {

    private static Core instance;
    private ListenerManager listenerManager;

    public List<MyManagers> myManagers = new ArrayList<>();

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        createInstance();

        listenerManager.load();
    }

    private void createInstance() {
        instance = this;
        listenerManager = new ListenerManager(this);
    }

    @Override
    public void onDisable() {
        for (MyManagers pluginManager : myManagers) {
            pluginManager.unload();
        }
    }

    public MyManagers registerPlugin(Plugin plugin) {
        MyManagers pluginManager = new MyManagers(plugin);
        myManagers.add(pluginManager);
        pluginManager.load();

        return pluginManager;
    }
}
