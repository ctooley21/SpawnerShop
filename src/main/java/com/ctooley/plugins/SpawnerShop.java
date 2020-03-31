package com.ctooley.plugins;

import com.ctooley.plugins.commands.Commands;
import com.ctooley.plugins.listeners.ShopListeners;
import com.ctooley.plugins.listeners.SignListener;
import com.ctooley.plugins.util.Util;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class SpawnerShop extends JavaPlugin 
{

    public static Economy economy = null;
    public HashMap<String, Long> cooldown = new HashMap<>();
    public FileConfiguration config = getConfig();

    public void onEnable() 
    {
        Bukkit.getServer().getPluginManager().registerEvents(new SignListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ShopListeners(this), this);
        initialiseConfig();
        getCommand("spawners").setExecutor(new Commands(this));
        setupEconomy();
        new Util(config);
    }

    private void initialiseConfig() 
    {
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    public void onDisable() 
    {

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
}