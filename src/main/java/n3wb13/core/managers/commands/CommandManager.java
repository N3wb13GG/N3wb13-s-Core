package n3wb13.core.managers.commands;

import n3wb13.core.managers.Manager;
import n3wb13.core.utils.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommandManager extends Manager {

    private List<MyCommand> myCommands = new ArrayList<>();

    public List<MyCommand> getMyCommands() {
        return myCommands;
    }

    @Override
    public void load() {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                    .setUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(plugin.getClass().getPackage().getName()))));
            Set list = reflections.getSubTypesOf(MyCommand.class);

            for (Object obj : list) {
                Class objClass = (Class) obj;
                if (objClass.getSuperclass() == MyCommand.class) {
                    MyCommand myCommand = (MyCommand) objClass.newInstance();
                    myCommands.add(myCommand);

                    final Field bCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                    bCommandMap.setAccessible(true);
                    CommandMap commandMap = (CommandMap) bCommandMap.get(Bukkit.getServer());

                    commandMap.register(plugin.getName(), myCommand);

                    registerSubCommands(myCommand);
                }
            }

        } catch (Exception e) {
            //ここに入った時点でアウト
            ServerUtil.errorLog(plugin, ChatColor.RED + "Reflection Error!", true);
        }
    }

    private void registerSubCommands(MyCommand myCommand) {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                    .setUrls(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toURL())
                    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(plugin.getClass().getPackage().getName()))));
            Set list1 = reflections.getSubTypesOf(myCommand.getClass());

            for (Object obj1 : list1) {
                Class objClass1 = (Class) obj1;
                if (objClass1.getSuperclass() == myCommand.getClass()) {
                    MyCommand subCommand = (MyCommand) objClass1.newInstance();

                    myCommand.addSubCommand(subCommand);

                    if (list1.size() > 0) registerSubCommands(subCommand);
                }
            }
        } catch (Exception e) {
            //ここに入った時点でアウト
            ServerUtil.errorLog(plugin, ChatColor.RED + "Reflection Error!", true);
        }
    }
}
