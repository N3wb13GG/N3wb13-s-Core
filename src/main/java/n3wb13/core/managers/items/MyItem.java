package n3wb13.core.managers.items;

import n3wb13.core.utils.itemnbtapi.NBTItem;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MyItem {

    private final Plugin plugin;
    private final String name;

    private ItemStack itemStack;

    public MyItem(Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    protected void setItemStack(ItemStack itemStack) {
        NBTItem nbtTag = new NBTItem(itemStack);
        nbtTag.setString("Plugin", plugin.getName());
        nbtTag.setString("Name", name);
        itemStack = nbtTag.getItem();

        this.itemStack = itemStack;
    }

    public void onItemUse(PlayerInteractEvent event) {
        return;
    }

    public void onDrop(PlayerDropItemEvent event) {
        return;
    }

    public void onItemSpawn(ItemSpawnEvent event) {
        return;
    }

    public void onInventoryClick(InventoryClickEvent event) {
        return;
    }
}
