package n3wb13.core.managers.listeners;

import n3wb13.core.managers.Manager;
import n3wb13.core.utils.ServerUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListenerManager extends Manager {

    private List<Listener> myListeners = new ArrayList<>();

    public List<Listener> getMyListners() {
        return myListeners;
    }

    @Override
    public void load() {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                    .setUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(plugin.getClass().getPackage().getName()))));
            Set list = reflections.getSubTypesOf(IMyListener.class);

            for (Object obj : list) {
                Class objClass = (Class) obj;
                Listener listener = (Listener) objClass.newInstance();
                myListeners.add(listener);
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
            }
        } catch (Exception e) {
            //ここに入った時点でアウト
            ServerUtil.errorLog(plugin, ChatColor.RED + "Reflection Error!", true);
        }
    }
}
