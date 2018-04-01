package n3wb13.core.managers;

import n3wb13.core.Core;
import n3wb13.core.utils.ServerUtil;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.*;

public class Managers {

    private Map<Plugin, List<Manager>> managers = new HashMap<>();

    public void setup(Plugin plugin) {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(Core.getInstance().getClass().getPackage().getName()))));
        Set list = reflections.getSubTypesOf(Manager.class);

        for (Object obj : list) {
            Class objClass = (Class) obj;
            try {
                Manager manager = (Manager) objClass.newInstance();
                addManager(plugin, manager);
            } catch (Exception e) {
                //ここに入った時点でアウト
                ServerUtil.errorLog(Core.getInstance(), ChatColor.RED + "Reflection Error!", true);
            }
        }
    }

    private void addManager(Plugin plugin, Manager manager) {
        if (managers.get(plugin) == null) managers.put(plugin, new ArrayList<>());
        if (!managers.get(plugin).contains(manager)) managers.get(plugin).add(manager);
    }

    public void loadManagers() {
        for (Plugin plugin : managers.keySet()) {
            for (Manager manager : managers.get(plugin)) {
                manager.load();
            }
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
}
