package com.ctooley.plugins.listeners;

import com.ctooley.plugins.SpawnerShop;
import com.ctooley.plugins.util.Util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class ShopListeners implements Listener {

    private final SpawnerShop plugin;
    private final Util util;

    public ShopListeners(SpawnerShop plugin, Util util) {
        this.plugin = plugin;
        this.util = util;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        String error = ChatColor.RED + "Error:" + ChatColor.DARK_RED + " You do not have sufficient funds.";
        if (ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase(ChatColor.stripColor(
                ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("options.shopname"))
        ))) {
            event.setCancelled(true);
            if ((clicked == null) || (clicked.getType() == Material.AIR)) {
                return;
            }
            String spawner = ChatColor.stripColor(clicked.getItemMeta().getDisplayName().replace("Spawner", "").replace(" ", ""));
            if(!player.hasPermission("spawnershop.buy." + spawner.toLowerCase()) && !player.hasPermission("spawnershop.buy.all")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.nopermission")));
                return;
            }
            if(SpawnerShop.economy.getBalance(player) >= plugin.getConfig().getInt("spawners." + spawner.toUpperCase().replace(" ", "_") + ".buy-price")) {
                util.handleSale(player, true, spawner);
                util.giveSpawner(player, spawner);
                player.closeInventory();
                plugin.cooldown.put(player.getName(), System.currentTimeMillis());
            }else {
                player.closeInventory();
                player.sendMessage(error);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(e.getBlock().getType() != Material.SPAWNER) return;
        if (!e.getItemInHand().hasItemMeta()) return;
        if (!e.getItemInHand().getItemMeta().hasDisplayName()) return;

        Block block = e.getBlock();
        String name = e.getItemInHand().getItemMeta().getDisplayName();
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(!(block.getState() instanceof CreatureSpawner)) return;
                CreatureSpawner spawner = (CreatureSpawner) block.getState();
                EntityType entityType = getEntityType(name.replace(" Spawner", ""));
                spawner.setSpawnedType(entityType);
                spawner.update();
            }
        }, 1L);
    }

    public EntityType getEntityType(String spawner)
    {
        for(EntityType type : EntityType.values())
        {
            if(type.getKey().getKey().equalsIgnoreCase(spawner)) return type;
        }
        return null;
    }
}