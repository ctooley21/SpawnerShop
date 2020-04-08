package com.ctooley.plugins.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.ctooley.plugins.SpawnerShop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util
{
    private ConfigurationSection spawnerSection;
    private FileConfiguration config;
    private String prefix;

    public Util(FileConfiguration config)
    {
        if(this.config != null)
        {
            return;
        }

        this.spawnerSection = config.getConfigurationSection("spawners");
        this.config = config;
        this.prefix = translateColors(config.getString("options.prefix"));
    }

    public void openInventory(Player player)
    {
        Inventory shopInventory = Bukkit.createInventory(null, config.getInt("options.inventorysize"), config.getString("options.shopname"));

        for(String spawnerKey : spawnerSection.getKeys(false)) 
        {
            if(!config.getBoolean("spawners." + spawnerKey + ".enabled"))
            {
                continue;
            }

            ItemStack spawner = new ItemStack(Material.getMaterial(spawnerKey + "_SPAWN_EGG"), 1);
            ItemMeta spawnerMeta = spawner.getItemMeta();
            spawnerMeta.setDisplayName(ChatColor.WHITE + formatSpawner(spawnerKey) + " Spawner");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(config.getInt("spawners." + spawnerKey + ".buy-price")));
            spawnerMeta.setLore(lore);
            spawner.setItemMeta(spawnerMeta);
            shopInventory.addItem(spawner);
        }

        player.openInventory(shopInventory);
    }

    public void giveSpawner(Player player, String mob) {
        ItemStack mobSpawner = new ItemStack(Material.SPAWNER);
        ItemMeta mobMeta = mobSpawner.getItemMeta();
        mobMeta.setDisplayName(ChatColor.WHITE + capFirst(mob) + " Spawner");
        mobSpawner.setItemMeta(mobMeta);
        player.getInventory().addItem(mobSpawner);
    }

    public String formatSpawner(String string) {
        string = string.replace("_", " ").toLowerCase();
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    public String capFirst(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    public String translateColors(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public void sendMessage(Player player, boolean prefix, String message)
    {
        if(prefix)
        {
            player.sendMessage(this.prefix + " " + translateColors(message));
        }
        else
        {
            player.sendMessage(translateColors(message));
        }
    }

    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean isSign(Material material)
    {
        return material.name().toLowerCase().contains("sign");
    }

    public void handleSale(Player player, boolean sale, int price, String spawner)
    {   
        if(sale)
        {
            sendMessage(player, false, ChatColor.GREEN + "" + config.getInt("spawners." + spawner + ".buy-price") + " has been taken from your account.");
            SpawnerShop.economy.withdrawPlayer(player, price);
        }
        else
        {
            sendMessage(player, false, ChatColor.GREEN + "" + config.getInt("spawners." + spawner + ".sell-price") + " has been deposited into your account.");
            SpawnerShop.economy.depositPlayer(player, price);
        }
    }
}