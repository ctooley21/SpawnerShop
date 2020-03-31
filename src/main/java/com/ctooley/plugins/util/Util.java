package com.ctooley.plugins.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    private static Inventory shopInventory;
    ConfigurationSection spawnerSection;
    FileConfiguration config;

    public Util(FileConfiguration config)
    {
        if(shopInventory == null)
        {
            this.spawnerSection = config.getConfigurationSection("spawners");
            this.config = config;
            setupInventory();
        }
    }

    private void setupInventory()
    {
        shopInventory = Bukkit.createInventory(null, config.getInt("options.inventorysize"), config.getString("options.shopname"));

        for(String spawnerKey : spawnerSection.getKeys(false)) 
        {
            if(!config.getBoolean("spawners." + spawnerKey + ".enabled"))
            {
                continue;
            }

            ItemStack spawner = new ItemStack(Material.getMaterial(spawnerKey + "_SPAWN_EGG"), 1);
            ItemMeta spawnerMeta = spawner.getItemMeta();
            spawnerMeta.setDisplayName(ChatColor.WHITE + Util.formatSpawner(spawnerKey) + " Spawner");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(config.getInt("spawners." + spawnerKey + ".buy-price")));
            spawnerMeta.setLore(lore);
            spawner.setItemMeta(spawnerMeta);
            shopInventory.addItem(spawner);
        }
    }

    public static void giveSpawner(Player p, String mob) {
        ItemStack mobSpawner = new ItemStack(Material.SPAWNER);
        ItemMeta mobMeta = mobSpawner.getItemMeta();
        mobMeta.setDisplayName(ChatColor.WHITE + capFirst(mob) + " Spawner");
        mobSpawner.setItemMeta(mobMeta);
        p.getInventory().addItem(mobSpawner);
    }

    public static String formatSpawner(String string) {
        string = string.replace("_", " ").toLowerCase();
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    public static String capFirst(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    public static void openInv(Player player) {
        player.openInventory(shopInventory);
    }
}