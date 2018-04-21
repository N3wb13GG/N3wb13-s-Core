package n3wb13.core.listeners.servers;

import n3wb13.core.Core;
import n3wb13.core.managers.MyManagers;
import n3wb13.core.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class ItemSpawnListener implements Listener, IMyListener {

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        for (MyManagers pluginManager : Core.getInstance().myManagers) {
            pluginManager.itemManager.onItemSpawn(event);
        }
    }
}
