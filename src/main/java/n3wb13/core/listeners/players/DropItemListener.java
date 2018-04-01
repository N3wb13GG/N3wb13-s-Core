package n3wb13.core.listeners.players;

import n3wb13.core.Core;
import n3wb13.core.managers.Manager;
import n3wb13.core.managers.items.ItemManager;
import n3wb13.core.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;

public class DropItemListener implements Listener, IMyListener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        for (Plugin plugin : Core.getInstance().managers.getManagers().keySet()) {
            for (Manager manager : Core.getInstance().managers.getManagers().get(plugin)) {
                if (manager instanceof ItemManager) ((ItemManager) manager).onDrop(event);
            }
        }
    }
}
