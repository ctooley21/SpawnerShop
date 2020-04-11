package com.ctooley.plugins;

import java.util.HashMap;
import java.util.logging.Level;

import com.ctooley.plugins.commands.Commands;
import com.ctooley.plugins.economy.ShopEconomy;
import com.ctooley.plugins.listeners.ShopListeners;
import com.ctooley.plugins.listeners.SignListener;
import com.ctooley.plugins.util.Logger;
import com.ctooley.plugins.util.SpawnerFile;
import com.ctooley.plugins.util.Util;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnerShop extends JavaPlugin 
{

    public ShopEconomy economy = null;
    private Util util;
    public Logger logger;
    public SpawnerFile spawnerFile;

    public HashMap<String, Long> cooldown = new HashMap<>();
    public static String currencySign;
    public FileConfiguration config;

    public void onEnable() 
    {
        initialiseConfig();
        logger = new Logger(this);
        util = new Util(this, config, spawnerFile.getConfig());
        registerListeners();
        initialiseCommands();
        currencySign = config.getString("options.currency-sign");
        enableMetrics();
        initialiseEconomy();
    }

    public void onDisable() 
    {

    }

    private void initialiseConfig() 
    {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        spawnerFile = new SpawnerFile(this);
    }

    private void initialiseCommands()
    {
        getCommand("spawners").setExecutor(new Commands(this, util));
    }
    
    private void registerListeners()
    {
        Bukkit.getServer().getPluginManager().registerEvents(new SignListener(this, util), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ShopListeners(this, util), this);
    }

    public void reload()
    {
        reloadConfig();
        config = getConfig();
        spawnerFile = new SpawnerFile(this);
        util = new Util(this, config, spawnerFile.getConfig());
        registerListeners();
        initialiseCommands();
        currencySign = config.getString("options.currency-sign");
    }

    public void enableMetrics()
    {
        int pluginId = 7068;
        new Metrics(this, pluginId);
    }

    public void initialiseEconomy()
    {
        economy = new ShopEconomy(this);
        if(!economy.enabled)
        {
            logger.Log(Level.SEVERE, "No economy plugin found! Please install either Vault or Reserve and a valid economy provider.");
            logger.Log(Level.SEVERE, "Disabling SpawnerShop.");
            economy = null;
            cooldown = null;
            config = null;
            util = null;
            currencySign = null;
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
}