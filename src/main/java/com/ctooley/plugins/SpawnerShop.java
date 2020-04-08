package com.ctooley.plugins;

import java.util.HashMap;

import com.ctooley.plugins.commands.Commands;
import com.ctooley.plugins.listeners.ShopListeners;
import com.ctooley.plugins.listeners.SignListener;
import com.ctooley.plugins.util.Util;
import com.ctooley.plugins.util.VaultAPI;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnerShop extends JavaPlugin 
{

    public static Economy economy = null;
    public VaultAPI vaultAPI;
    public HashMap<String, Long> cooldown = new HashMap<>();
    public FileConfiguration config;
    private Util util;
    public static String currencySign;

    public void onEnable() 
    {
        initialiseConfig();
        util = new Util(config);
        vaultAPI = new VaultAPI(this);
        registerListeners();
        initialiseCommands();
        currencySign = config.getString("options.currencysign");
        setupEconomy();
    }

    public void onDisable() 
    {

    }

    private void initialiseConfig() 
    {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
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

    private boolean setupEconomy() 
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);

        if (economyProvider != null) 
        {
            economy = economyProvider.getProvider();
        }

        return economy != null;
    }

    public void reload()
    {
        reloadConfig();
        config = getConfig();
        util = new Util(config);
        registerListeners();
        initialiseCommands();
        currencySign = config.getString("options.currencysign");
    }
}