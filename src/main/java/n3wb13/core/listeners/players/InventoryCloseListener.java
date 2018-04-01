package n3wb13.core.listeners.players;

import n3wb13.core.Core;
import n3wb13.core.managers.Manager;
import n3wb13.core.managers.guis.GuiManager;
import n3wb13.core.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

public class InventoryCloseListener implements Listener, IMyListener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        for (Plugin plugin : Core.getInstance().managers.getManagers().keySet()) {
            for (Manager manager : Core.getInstance().managers.getManagers().get(plugin)) {
                if (manager instanceof GuiManager) ((GuiManager) manager).onInventoryClose(event);
            }
        }
    }
}
