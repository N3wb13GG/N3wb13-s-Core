package n3wb13.core.managers.guis;

import n3wb13.core.managers.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GuiManager extends Manager {

    private Map<Inventory, MyGui> myGuis = new HashMap<>();
    private Map<MyGui, Player> viwes = new HashMap<>();

    public MyGui getMyGui(Inventory inventory) {
        return myGuis.get(inventory);
    }

    public void openMyGui(Player player, MyGui myGui) {
        player.openInventory(myGui.getInventory());
        myGuis.put(myGui.getInventory(), myGui);
        viwes.put(myGui, player);
    }

    public void closedMyGui(Inventory inventory) {
        if (myGuis.containsKey(inventory)) {
            myGuis.remove(inventory);
            viwes.remove(getMyGui(inventory));
        }
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != null) {
            MyGui myGui = getMyGui(event.getInventory());

            if (myGui != null)
                myGui.onInventoryClick(event);

            if (event.isCancelled()) {
                Player plaeyr = (Player) event.getWhoClicked();
                plaeyr.updateInventory();
            }
        }
    }

    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() != null) {
            MyGui myGui = getMyGui(event.getInventory());

            if (myGui != null) {
                myGui.onInventoryClose(event);
                closedMyGui(event.getInventory());
            }
        }
    }
}
