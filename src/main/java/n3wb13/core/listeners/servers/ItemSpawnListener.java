package n3wb13.core.listeners.servers;

import n3wb13.core.Core;
import n3wb13.core.managers.Manager;
import n3wb13.core.managers.items.ItemManager;
import n3wb13.core.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.plugin.Plugin;

public class ItemSpawnListener implements Listener, IMyListener {

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        for (Plugin plugin : Core.getInstance().managers.getManagers().keySet()) {
            for (Manager manager : Core.getInstance().managers.getManagers().get(plugin)) {
                if (manager instanceof ItemManager) ((ItemManager) manager).onItemSpawn(event);
            }
        }
    }
}
