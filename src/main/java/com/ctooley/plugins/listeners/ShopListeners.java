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
    private String inventoryTitle;

    public ShopListeners(SpawnerShop plugin, Util util) {
        this.plugin = plugin;
        this.util = util;
        this.inventoryTitle = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.shop-name")));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        if (ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase(inventoryTitle))
        {
            event.setCancelled(true);
            if ((clicked == null) || (clicked.getType() == Material.AIR)) return;

            String spawner = ChatColor.stripColor(clicked.getItemMeta().getDisplayName().replace("Spawner", "").replace(" ", ""));
            if(!player.hasPermission("spawnershop.buy." + spawner.toLowerCase()) && !player.hasPermission("spawnershop.buy.all")) {
                util.sendMessage(player, true, plugin.config.getString("options.no-permission"));
                return;
            }
            
            util.handleSale(player, true, true, spawner);
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