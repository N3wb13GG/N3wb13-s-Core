package n3wb13.core.managers.items;

import n3wb13.core.managers.Manager;
import n3wb13.core.utils.CoreLogger;
import n3wb13.core.utils.itemnbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ItemManager extends Manager {

    private Map<String, MyItem> myItems = new HashMap<>();

    public ItemManager(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                    .setUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(plugin.getClass().getPackage().getName()))));
            Set list = reflections.getSubTypesOf(MyItem.class);

            for (Object obj : list) {
                Class objClass = (Class) obj;
                MyItem myItem = (MyItem) objClass.newInstance();

                myItems.put(myItem.getName(), myItem);
            }
        } catch (Exception e) {
            //ここに入った時点でアウト
            CoreLogger.errorLog(CoreLogger.getNameFormat(plugin) + ChatColor.RED + "Reflection Error!", true);
        }
    }

    public Map<String, MyItem> getMyItems() {
        return myItems;
    }

    public MyItem getMyItem(String name) {
        return myItems.get(name.toLowerCase());
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null || event.getCursor() != null) {
            NBTItem currentNBT = new NBTItem(new ItemStack(Material.AIR));
            NBTItem cursorNBT = currentNBT;

            if (event.getCurrentItem() != null) currentNBT = new NBTItem(event.getCurrentItem());
            if (event.getCursor() != null) cursorNBT = new NBTItem(event.getCurrentItem());

            if (currentNBT.getString("Plugin").equals(plugin.getName()) || cursorNBT.getString("Plugin").equals(plugin.getName())) {
                if (myItems.containsKey(currentNBT.getString("Name")))
                    myItems.get(currentNBT.getString("Name")).onInventoryClick(event);
                if (myItems.containsKey(cursorNBT.getString("Name")))
                    myItems.get(cursorNBT.getString("Name")).onInventoryClick(event);
            }
        }
    }

    public void onDrop(PlayerDropItemEvent event) {
        NBTItem nbtTag = new NBTItem(event.getItemDrop().getItemStack());
        if (nbtTag.getString("Plugin").equals(plugin.getName()) && myItems.containsKey(nbtTag.getString("Name")))
            myItems.get(nbtTag.getString("Name")).onDrop(event);
    }

    public void onItemSpawn(ItemSpawnEvent event) {
        NBTItem nbtTag = new NBTItem(event.getEntity().getItemStack());
        if (nbtTag.getString("Plugin").equals(plugin.getName()) && myItems.containsKey(nbtTag.getString("Name")))
            myItems.get(nbtTag.getString("Name")).onItemSpawn(event);
    }

    public void onItemUse(PlayerInteractEvent event) {
        NBTItem nbtTag = new NBTItem(event.getItem());
        if (nbtTag.getString("Plugin").equals(plugin.getName()) && myItems.containsKey(nbtTag.getString("Name")))
            myItems.get(nbtTag.getString("Name")).onItemUse(event);
    }
}
