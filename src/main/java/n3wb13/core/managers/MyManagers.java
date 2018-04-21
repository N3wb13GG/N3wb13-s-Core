package n3wb13.core.managers;

import n3wb13.core.managers.commands.CommandManager;
import n3wb13.core.managers.guis.GuiManager;
import n3wb13.core.managers.items.ItemManager;
import n3wb13.core.managers.listeners.ListenerManager;
import n3wb13.core.managers.scoreboards.ScoreboardManager;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public final class MyManagers extends Manager {

    public final ListenerManager listenerManager;
    public final ScoreboardManager scoreboardManager;
    public final CommandManager commandManager;
    public final ItemManager itemManager;
    public final GuiManager guiManager;
    private List<Manager> managers = new ArrayList<>();

    public MyManagers(Plugin plugin) {
        super(plugin);

        listenerManager = new ListenerManager(plugin);
        managers.add(listenerManager);
        scoreboardManager = new ScoreboardManager(plugin);
        managers.add(scoreboardManager);
        commandManager = new CommandManager(plugin);
        managers.add(commandManager);
        itemManager = new ItemManager(plugin);
        managers.add(itemManager);
        guiManager = new GuiManager(plugin);
        guiManager.plugin.getServer();
        managers.add(guiManager);
    }

    public void load() {
        for (Manager manager : managers) manager.load();
    }

    public void unload() {
        for (Manager manager : managers) manager.unload();
    }
}
