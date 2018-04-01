package n3wb13.core.listeners.players;

import n3wb13.core.Core;
import n3wb13.core.managers.Manager;
import n3wb13.core.managers.guis.GuiManager;
import n3wb13.core.managers.items.ItemManager;
import n3wb13.core.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class InventoryClickListener implements Listener, IMyListener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        for (Plugin plugin : Core.getInstance().managers.getManagers().keySet()) {
            for (Manager manager : Core.getInstance().managers.getManagers().get(plugin)) {
                if (manager instanceof ItemManager) ((ItemManager) manager).onInventoryClick(event);
                else if (manager instanceof GuiManager) ((GuiManager) manager).onInventoryClick(event);
            }
        }
    }
}
