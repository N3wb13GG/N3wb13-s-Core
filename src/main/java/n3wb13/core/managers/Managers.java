package n3wb13.core.managers;

import n3wb13.core.Core;
import n3wb13.core.utils.ServerUtil;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.*;

public class Managers {

    private Map<Plugin, List<Manager>> managers = new HashMap<>();

    public void setup(Plugin plugin) {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                    .setUrls(Core.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(Core.getInstance().getClass().getPackage().getName()))));
            Set list = reflections.getSubTypesOf(Manager.class);

            for (Object obj : list) {
                Class objClass = (Class) obj;
                Manager manager = (Manager) objClass.newInstance();
                manager.setPlugin(plugin);
                addManager(plugin, manager);
            }

        } catch (Exception e) {
            //ここに入った時点でアウト
            ServerUtil.errorLog(Core.getInstance(), ChatColor.RED + "Reflection Error!", true);
        }
    }

    private void addManager(Plugin plugin, Manager manager) {
        if (managers.get(plugin) == null)
            managers.put(plugin, new ArrayList<>());
        if (!managers.get(plugin).contains(manager))
            managers.get(plugin).add(manager);
    }

    public void loadManagers() {
        for (Plugin plugin : managers.keySet()) {
            loadManagers(plugin);
        }
    }

    public void loadManagers(Plugin plugin) {
        for (Manager manager : managers.get(plugin)) {
            manager.load();
        }
    }

    public void unloadManagers() {
        for (Plugin plugin : managers.keySet()) {
            for (Manager manager : managers.get(plugin)) {
                manager.unload();
            }
        }
    }

    public Map<Plugin, List<Manager>> getManagers() {
        return managers;
    }

    public <T extends Manager> T getManager(Plugin plugin, Class<? extends T> manager) {
        for (Manager m : managers.get(plugin)) {
            if (m.getClass() == manager)
                return (T) m;
        }
        return null;
    }
}
