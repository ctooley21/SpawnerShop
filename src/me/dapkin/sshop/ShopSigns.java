package me.dapkin.sshop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
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
                    if (line3.equalsIgnoreCase("Enderman")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Enderman");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.enderman")));
                        }
                    } else if (line3.equalsIgnoreCase("Blaze")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Blaze");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.blaze")));
                        }
                    } else if (line3.equalsIgnoreCase("Skeleton")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Skeleton");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.skeleton")));
                        }
                    } else if (line3.equalsIgnoreCase("Zombie")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Zombie");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.zombie")));
                        }
                    } else if (line3.equalsIgnoreCase("Creeper")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Creeper");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.creeper")));
                        }
                    } else if (line3.equalsIgnoreCase("CaveSpider")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Cave Spider");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.cavespider")));
                        }
                    } else if (line3.equalsIgnoreCase("Spider")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Spider");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.spider")));
                        }
                    } else if (line3.equalsIgnoreCase("Wolf")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Wolf");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.wolf")));
                        }
                    } else if (line3.equalsIgnoreCase("Chicken")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Chicken");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.chicken")));
                        }
                    } else if (line3.equalsIgnoreCase("Cow")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Cow");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.cow")));
                        }
                    } else if (line3.equalsIgnoreCase("Pig")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Pig");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.pig")));
                        }
                    } else if (line3.equalsIgnoreCase("Ocelot")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Ocelot");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.ocelot")));
                        }
                    } else if (line3.equalsIgnoreCase("Mushroom Cow")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Mushroom Cow");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.mushroom")));
                        }
                    } else if (line3.equalsIgnoreCase("Sheep")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Sheep");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.sheep")));
                        }
                    } else if (line3.equalsIgnoreCase("Bat")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Bat");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.bat")));
                        }
                    } else if (line3.equalsIgnoreCase("Rabbit")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Rabbit");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.rabbit")));
                        }
                    } else if (line3.equalsIgnoreCase("Squid")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Squid");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.squid")));
                        }
                    } else if (line3.equalsIgnoreCase("Villager")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Villager");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.villager")));
                        }
                    } else if (line3.equalsIgnoreCase("ZombiePigman")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Zombie Pigman");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.zombiepig")));
                        }
                    } else if (line3.equalsIgnoreCase("Silverfish")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Silverfish");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.silverfish")));
                        }
                    } else if (line3.equalsIgnoreCase("IronGolem")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Iron Golem");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.irongolem")));
                        }
                    } else if (line3.equalsIgnoreCase("Slime")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Slime");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.slime")));
                        }
                    } else if (line3.equalsIgnoreCase("Horse")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Horse");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.horse")));
                        }
                    } else if (line3.equalsIgnoreCase("Witch")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Witch");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.witch")));
                        }
                    } else if (line3.equalsIgnoreCase("Magma Cube")) {
                        e.setLine(0, ChatColor.BLUE + "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "Magma Cube");
                        if (isInt(price)) {
                            int price1 = Integer.parseInt(price);
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(price1));
                        } else {
                            p.sendMessage("You have either not entered a price, or entered one incorrectly. The price has been set to the one specified in the config!");
                            e.setLine(3, plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(plugin.getConfig().getInt("prices.magmacube")));
                        }
                    } else {
                        p.sendMessage("You have incorrectly formatted a SPAWNERSHOP sign!");
                        p.sendMessage("Here is the correct FORMAT!");
                        e.setLine(0, "[SpawnerShop]");
                        e.setLine(1, "Buy");
                        e.setLine(2, "<MobType>");
                        e.setLine(3, "Price");
                    }
                } else {
                    p.sendMessage("You have incorrectly formatted a SPAWNERSHOP sign!");
                    p.sendMessage("Here is the correct FORMAT!");
                    e.setLine(0, "[SpawnerShop]");
                    e.setLine(1, "Buy");
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
                        SpawnerShop.economy.depositPlayer(p, realPrice);
                        plugin.giveSpawner(p, line3);
                        p.sendMessage(ChatColor.GREEN + plugin.config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(realPrice) + " has been deposited into your account.");
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.nopermission")));
                }
            }
        }
    }
}