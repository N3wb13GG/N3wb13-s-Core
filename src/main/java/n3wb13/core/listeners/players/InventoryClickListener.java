package n3wb13.core.listeners.players;

import n3wb13.core.Core;
import n3wb13.core.managers.MyManagers;
import n3wb13.core.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener, IMyListener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        for (MyManagers pluginManager : Core.getInstance().myManagers) {
            pluginManager.itemManager.onInventoryClick(event);
            pluginManager.guiManager.onInventoryClick(event);
        }
    }
}
