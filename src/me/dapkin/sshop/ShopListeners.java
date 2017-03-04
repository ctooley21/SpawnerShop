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
    public final ShopPlugin plugin;

    public ShopListeners(ShopPlugin plugin) {
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
            ChatColor white = ChatColor.WHITE;
            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Enderman Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.enderman")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.enderman"));
                    plugin.giveSpawner(player, "Enderman");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.enderman")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.closeInventory();
                    player.sendMessage(error);
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Blaze Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.blaze")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.blaze"));
                    plugin.giveSpawner(player, "Blaze");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.blaze")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Skeleton Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.skeleton")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.skeleton"));
                    plugin.giveSpawner(player, "Skeleton");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.skeleton")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Zombie Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.zombie")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.zombie"));
                    plugin.giveSpawner(player, "Zombie");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.zombie")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Creeper Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.creeper")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.creeper"));
                    plugin.giveSpawner(player, "Creeper");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.creeper")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Cave Spider Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.cavespider")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.cavespider"));
                    plugin.giveSpawner(player, "Cave Spider");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.cavespider")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Spider Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.spider")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.spider"));
                    plugin.giveSpawner(player, "Spider");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.spider")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().contains("Wolf Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.wolf")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.wolf"));
                    plugin.giveSpawner(player, "Wolf");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.wolf")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Chicken Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.chicken")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.chicken"));
                    plugin.giveSpawner(player, "Chicken");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.chicken")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Cow Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.cow")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.cow"));
                    plugin.giveSpawner(player, "Cow");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.cow")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Pig Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.pig")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.pig"));
                    plugin.giveSpawner(player, "Pig");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.pig")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Ocelot Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.ocelot")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.ocelot"));
                    plugin.giveSpawner(player, "Ocelot");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.ocelot")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Mushroom Cow Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.mushroom")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.mushroom"));
                    plugin.giveSpawner(player, "Mushroom Cow");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.mushroom")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Sheep Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.sheep")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.sheep"));
                    plugin.giveSpawner(player, "Sheep");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.sheep")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Bat Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.bat")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.bat"));
                    plugin.giveSpawner(player, "Bat");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.bat")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Rabbit Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.rabbit")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.rabbit"));
                    plugin.giveSpawner(player, "Rabbit");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.rabbit")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Squid Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.squid")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.squid"));
                    plugin.giveSpawner(player, "Squid");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.squid")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Villager Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.villager")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.villager"));
                    plugin.giveSpawner(player, "Villager");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.villager")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Zombie Pigman Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.zombiepig")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.zombiepig"));
                    plugin.giveSpawner(player, "Zombie Pigman");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.zombiepig")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Silverfish Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.silverfish")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.silverfish"));
                    plugin.giveSpawner(player, "Silverfish");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.silverfish")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Iron Golem Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.irongolem")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.irongolem"));
                    plugin.giveSpawner(player, "Iron Golem");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.irongolem")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Slime Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.slime")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.slime"));
                    plugin.giveSpawner(player, "Slime");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.slime")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Horse Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.horse")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.horse"));
                    plugin.giveSpawner(player, "Horse");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.horse")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Witch Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.witch")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.witch"));
                    plugin.giveSpawner(player, "Witch");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.witch")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(white + "Magma Cube Spawner")) {
                if (ShopPlugin.economy.getBalance(player) >= plugin.getConfig().getInt("prices.magmacube")) {
                    ShopPlugin.economy.withdrawPlayer(player, plugin.getConfig().getInt("prices.magmacube"));
                    plugin.giveSpawner(player, "MagmaCube");
                    player.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.magmacube")) + " has been taken from your account.");
                    player.closeInventory();
                    plugin.cooldown.put(player.getName(), System.currentTimeMillis());
                } else {
                    player.sendMessage(error);
                    player.closeInventory();
                }
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