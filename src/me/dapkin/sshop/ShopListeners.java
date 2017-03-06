package me.dapkin.sshop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.Locale;

public class ShopListeners implements Listener {
    private final SpawnerShop plugin;

    ShopListeners(SpawnerShop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        String error = ChatColor.RED + "Error:" + ChatColor.DARK_RED + " You do not have sufficient funds.";
        if (inventory.getName().equals(plugin.spawnerInv.getName())) {
            event.setCancelled(true);
            if ((clicked == null) || (clicked.getType() == Material.AIR)) {
                return;
            }
            String spawner = clicked.getItemMeta().getDisplayName().replace("Spawner", "").replace(" ", "").toLowerCase();
            if(SpawnerShop.economy.getBalance(player) >= plugin.getConfig().getInt("prices." + spawner)) {
                SpawnerShop.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices." + spawner));
                plugin.giveSpawner(player, spawner);
                player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices." + spawner)) + " has been taken from your account.");
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
        if (!e.getItemInHand().hasItemMeta()) return;
        if (!e.getItemInHand().getItemMeta().hasDisplayName()) return;

        String type = ChatColor.stripColor(e.getItemInHand().getItemMeta().getDisplayName()).replace(" Spawner", "");
        plugin.updateSpawner(e.getBlock(), type);
    }
}