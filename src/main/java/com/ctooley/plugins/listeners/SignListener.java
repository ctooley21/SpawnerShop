package com.ctooley.plugins.listeners;

import com.ctooley.plugins.SpawnerShop;
import com.ctooley.plugins.util.ShopSign;
import com.ctooley.plugins.util.Util;

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

public class SignListener implements Listener {

    private final SpawnerShop plugin;
    private final Util util;

    public SignListener(SpawnerShop plugin, Util util) {
        this.plugin = plugin;
        this.util = util;
    }

    @EventHandler
    public void onSignPlacement(SignChangeEvent e) {
        Player p = e.getPlayer();
        if (e.getLine(0).equals("[SpawnerShop]")) {
            if ((p.hasPermission("spawnershop.signs.create")) || p.isOp()) {
                String line2 = e.getLine(1);
                String line3 = e.getLine(2);
                if (line2.equalsIgnoreCase("Buy")) {
                    String price = e.getLine(3).replace(plugin.config.getString("options.currencysign"), "");

                    ConfigurationSection spawnerSection = plugin.config.getConfigurationSection("spawners");
                    if(spawnerSection.contains(line3.toUpperCase())) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");

                        if (util.isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("spawners." + line3 + ".buy-price")));
                        }
                    } else {
                        sendFormatMessage(e);
                    }
                } else if(line2.equalsIgnoreCase("Sell")) {
                    String price = e.getLine(3);
                    ConfigurationSection spawnerSection = plugin.config.getConfigurationSection("spawners");
                    if(spawnerSection.contains(line3.toUpperCase())) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");

                        if (util.isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("spawners." + line3 + ".sell-price")));
                        }
                    } else {
                        sendFormatMessage(e);
                    }
                } else {
                   sendFormatMessage(e);
                }
            } else {
                util.sendMessage(p, true, plugin.config.getString("options.nopermission"));
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) 
    {
        Player p = e.getPlayer();
        if(e.getClickedBlock() == null)
        {
            return;
        }
        boolean isSign = util.isSign(e.getClickedBlock().getType());
        if ((e.getAction() != Action.RIGHT_CLICK_BLOCK) || !isSign) return;

        ShopSign sign = new ShopSign((Sign) e.getClickedBlock().getState());
        if (!sign.isValid()) return;

        if ((!p.hasPermission("spawnershop.signs.use")) && !p.isOp()) 
        {
            util.sendMessage(p, true, plugin.config.getString("options.nopermission"));
            return;
        }

        String spawner = sign.getSpawnerType();
        String method = sign.getMethod();
        if(!p.hasPermission("spawnershop." + method + "." + spawner.toLowerCase()) && !p.hasPermission("spawnershop." + method + ".all")) 
        {
            util.sendMessage(p, true, plugin.config.getString("options.nopermission"));
            return;
        }
        
        int price = sign.getPrice();
        if (method.equalsIgnoreCase("Buy"))
        {
            if(SpawnerShop.economy.getBalance(p) >= price) {
                SpawnerShop.economy.withdrawPlayer(p, price);
                util.giveSpawner(p, spawner);
                p.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price) + " has been taken from your account.");
            }
            else 
            {
                util.sendMessage(p, true, plugin.config.getString("options.nomoney"));
            }
        }
        else 
        {
            if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) 
            {
                if(p.getInventory().getItemInMainHand().getType() == Material.SPAWNER) 
                {
                    if(!p.getInventory().getItemInMainHand().hasItemMeta() || !p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) return;
                    String name = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                    if(name.contains(ChatColor.COLOR_CHAR+"")) 
                    {
                        if(ChatColor.stripColor(name).replace(" Spawner","").equalsIgnoreCase(spawner)) 
                        {
                            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
                            SpawnerShop.economy.depositPlayer(p, price);
                            p.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price) + " has been deposited into your account.");
                        }
                    }
                }
            }
        }
    }

    private void sendFormatMessage(SignChangeEvent e) 
    {
        Player p = e.getPlayer();
        p.sendMessage("You have incorrectly formatted a SPAWNERSHOP sign!");
        p.sendMessage("Here is the correct FORMAT!");
        e.setLine(0, "[SpawnerShop]");
        e.setLine(1, "Buy/Sell");
        e.setLine(2, "<MobType>");
        e.setLine(3, "Price");
    }
}