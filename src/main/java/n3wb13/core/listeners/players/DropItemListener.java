package n3wb13.core.listeners.players;

import n3wb13.core.Core;
import n3wb13.core.managers.MyManagers;
import n3wb13.core.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener, IMyListener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        for (MyManagers pluginManager : Core.getInstance().myManagers) {
            pluginManager.itemManager.onDrop(event);
        }
    }
}
