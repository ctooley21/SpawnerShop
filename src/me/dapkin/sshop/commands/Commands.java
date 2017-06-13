package me.dapkin.sshop.commands;

import me.dapkin.sshop.SpawnerShop;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private SpawnerShop plugin;

    public Commands(SpawnerShop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (p.hasPermission("spawnershop.use")) {
                    if (plugin.cooldown.containsKey(p.getName())) {
                        int cooldown_time = plugin.config.getInt("options.cooldown");
                        long diff = (System.currentTimeMillis() - plugin.cooldown.get(p.getName())) / 1000L;
                        if (diff < cooldown_time) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.cooldownmessage")));
                        } else {
                            plugin.openInv(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.openmessage")));
                            plugin.cooldown.remove(p.getName());
                        }
                    } else {
                        plugin.openInv(p);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.openmessage")));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.nopermission")));
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission("spawnershop.reload") || p.isOp()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.reloadsuccess")));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.prefix")) + " " + ChatColor.translateAlternateColorCodes('&', plugin.config.getString("options.nopermission")));
                    }
                } else if (args[0].equalsIgnoreCase("give")) {
                    p.sendMessage(ChatColor.GREEN + "Please choose a spawner type!");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (p.hasPermission("spawnershop.give")) {
                        plugin.giveSpawner(p, args[1]);
                    } else {
                        p.sendMessage(ChatColor.RED + "No permission!");
                    }
                }
            }
        }
        return true;
    }
}
