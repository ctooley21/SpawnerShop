package com.ctooley.plugins.util;

import java.util.logging.Level;

import com.ctooley.plugins.SpawnerShop;

import org.bukkit.Bukkit;

public class Logger 
{
    private String prefix;

    public Logger(SpawnerShop spawnerShop)
    {
        this.prefix = spawnerShop.config.getString("options.prefix");
    }

    public void Log(Level level, String message)
    {
        Bukkit.getLogger().log(level, prefix + " " + message);
    }
}