package com.ctooley.plugins;

import com.ctooley.plugins.commands.Commands;
import com.ctooley.plugins.listeners.ShopListeners;
import com.ctooley.plugins.listeners.SignListener;
import com.ctooley.plugins.util.Util;
import com.ctooley.plugins.util.VaultAPI;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class SpawnerShop extends JavaPlugin 
{

    public static Economy economy = null;
    public VaultAPI vaultAPI;
    public HashMap<String, Long> cooldown = new HashMap<>();
    public FileConfiguration config = getConfig();
    private Util util;

    public void onEnable() 
    {
        initialiseConfig();
        util = new Util(config);
        vaultAPI = new VaultAPI(this);
        registerListeners();
        initialiseCommands();
        new Util(config);
    }

    public void onDisable() 
    {

    }

    private void initialiseConfig() 
    {
        FileConfiguration config = getConfig();
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
}