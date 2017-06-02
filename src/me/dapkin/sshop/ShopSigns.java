package me.dapkin.sshop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.text.NumberFormat;
import java.util.Locale;

public class ShopSigns implements Listener {
    private SpawnerShop plugin;

    ShopSigns(SpawnerShop plugin) {
        this.plugin = plugin;
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @EventHandler
    public void onSignPlacement(SignChangeEvent e) {
        Player p = e.getPlayer();
        if (e.getLine(0).equals("[SpawnerShop]")) {
            if ((p.hasPermission("spawnershop.signs.create")) || p.isOp()) {
                String line2 = e.getLine(1);
                String line3 = e.getLine(2);
                if (line2.equalsIgnoreCase("Buy")) {
                    String price = e.getLine(3);

                    ConfigurationSection spawnerSection = plugin.config.getConfigurationSection("spawners");
                    if(spawnerSection.contains(line3)) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, line3);

                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("spawners." + line3 + ".buy-price")));
                        }
                    } else {
                        p.sendMessage("You have incorrectly formatted a SPAWNERSHOP sign!");
                        p.sendMessage("Here is the correct FORMAT!");
                        e.setLine(0, "[SpawnerShop]");
                        e.setLine(1, "Buy/Sell");
                        e.setLine(2, "<MobType>");
                        e.setLine(3, "Price");
                    }
                } else if(line2.equalsIgnoreCase("Sell")) {
                    String price = e.getLine(3);
                    ConfigurationSection spawnerSection = plugin.config.getConfigurationSection("spawners");
                    if(spawnerSection.contains(line3)) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Sell");
                        e.setLine(2, line3);

                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("spawners." + line3 + ".sell-price")));
                        }
                    } else {
                        p.sendMessage("You have incorrectly formatted a SPAWNERSHOP sign!");
                        p.sendMessage("Here is the correct FORMAT!");
                        e.setLine(0, "[SpawnerShop]");
                        e.setLine(1, "Buy/Sell");
                        e.setLine(2, "<MobType>");
                        e.setLine(3, "Price");
                    }
                } else {
                    p.sendMessage("You have incorrectly formatted a SPAWNERSHOP sign!");
                    p.sendMessage("Here is the correct FORMAT!");
                    e.setLine(0, "[SpawnerShop]");
                    e.setLine(1, "Buy/Sell");
                    e.setLine(2, "<MobType>");
                    e.setLine(3, "Price");
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.nopermission")));
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (
                (e.getClickedBlock().getType() == Material.SIGN) || (e.getClickedBlock().getType() == Material.WALL_SIGN) || (e.getClickedBlock().getType() == Material.SIGN_POST))) {
            Sign sign = (Sign) e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.BLUE + "[SpawnerShop]")) {
                if ((p.hasPermission("spawnershop.signs.use")) || (p.isOp())) {
                    String line3 = sign.getLine(2);
                    String error = ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.nomoney"));
                    String price = sign.getLine(3);
                    price = price.replace(plugin.config.getString("options.currencysign"), "");
                    price = price.replace(",", "");
                    int realPrice = Integer.parseInt(price);
                    if (sign.getLine(1).equalsIgnoreCase("Buy")) {
                        if(SpawnerShop.economy.getBalance(p) >= realPrice) {
                            SpawnerShop.economy.withdrawPlayer(p, realPrice);
                            plugin.giveSpawner(p, line3);
                            p.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(realPrice) + " has been taken from your account.");
                        }else {
                            p.sendMessage(error);
                        }
                    }else {
                        if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                            if(p.getInventory().getItemInMainHand().getType() == Material.MOB_SPAWNER) {
                                if(!p.getInventory().getItemInMainHand().hasItemMeta()) return;
                                if(!p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) return;
                                String name = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                                if(name.contains(ChatColor.COLOR_CHAR+"")) {
                                    if(ChatColor.stripColor(name).replace(" Spawner","").equalsIgnoreCase(line3)) {
                                        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
                                        SpawnerShop.economy.depositPlayer(p, realPrice);
                                        p.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(realPrice) + " has been deposited into your account.");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.nopermission")));
                }
            }
        }
    }
}