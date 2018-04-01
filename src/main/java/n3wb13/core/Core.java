package n3wb13.core;

import n3wb13.core.managers.Managers;
import n3wb13.core.managers.listeners.ListenerManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    public Managers managers;
    private ListenerManager listenerManager;

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        managers = new Managers();

        //アイテムとGUI用のリスナーを読み込む、正直この方法でマネージャークラスを生成しないで欲しいけどやっちゃう
        listenerManager = new ListenerManager();
        listenerManager.setPlugin(this);
        listenerManager.load();
    }

    @Override
    public void onDisable() {
        managers.unloadManagers();
    }

    public void registerPlugin(Plugin plugin) {
        managers.setup(plugin);
        managers.loadManagers(plugin);
    }
}
