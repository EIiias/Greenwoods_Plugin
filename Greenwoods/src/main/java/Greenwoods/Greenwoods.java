package Greenwoods;

import Commands.GreenwoodsCommand;
import Commands.InvisibleitemframesCommand;
import Commands.CountdownCommand;
import Economy.Vault;
import Events.*;
import Timer.Timer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Greenwoods extends JavaPlugin {

    private static Greenwoods instance;
    public static Timer timerInstance;

    @Override
    public void onEnable() {

        instance = this;

        //Disable Vault if not working
        if (!Vault.setupEconomy() ) {
            System.out.println("No economy plugin found. Disabling Vault!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //Register commands
        getCommand("countdown").setExecutor(new CountdownCommand());
        getCommand("countdown").setTabCompleter(new CountdownCommand());
        getCommand("invisibleitemframes").setExecutor(new InvisibleitemframesCommand());
        getCommand("invisibleitemframes").setTabCompleter(new InvisibleitemframesCommand());
        getCommand("greenwoods").setExecutor(new GreenwoodsCommand());

        //Register events
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerEntityInteract(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClose(), this);

        //Create new timer instance
        timerInstance = new Timer(false, 0);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Greenwoods getInstance() {
        return instance;
    }
}

