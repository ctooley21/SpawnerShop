package com.ctooley.plugins.commands;

import com.ctooley.plugins.SpawnerShop;
import com.ctooley.plugins.util.Util;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private final SpawnerShop plugin;
    private final Util util;

    public Commands(SpawnerShop plugin, Util util) {
        this.plugin = plugin;
        this.util = util;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        if (!(sender instanceof Player))
        {
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) 
        {
            executeGeneralCommand(player);
        } 
        else if (args.length == 1) 
        {
            if (args[0].equalsIgnoreCase("reload")) 
            {
                executeReloadCommand(player, args);
            } 
            else if (args[0].equalsIgnoreCase("give")) 
            {
                player.sendMessage(ChatColor.GREEN + "Please choose a spawner type!");
            }
        } 
        else if (args.length == 2) 
        {
            if (args[0].equalsIgnoreCase("give")) 
            {
                util.giveSpawner(player, args[1]);
            }
        }
        return true;
    }

    private void executeReloadCommand(Player player, String[] args)
    {
        if (!player.hasPermission("spawnershop.reload") && !player.isOp()) 
        {
            util.sendMessage(player, true, plugin.config.getString("options.nopermission"));
            return;
        } 

        util.sendMessage(player, true, plugin.config.getString("options.reloadsuccess"));
        plugin.reload();
    }

    private void executeGeneralCommand(Player player)
    {
        if (!player.hasPermission("spawnershop.use")) 
        {
            util.sendMessage(player, true, plugin.config.getString("options.nopermission"));
            return;
        } 

        if (plugin.cooldown.containsKey(player.getName()) && !player.isOp()) 
        {
            int cooldown_time = plugin.config.getInt("options.cooldown");
            long diff = (System.currentTimeMillis() - plugin.cooldown.get(player.getName())) / 1000L;
            if (diff < cooldown_time) {
                util.sendMessage(player, true, plugin.config.getString("options.cooldownmessage").replace("{time}", (int)(cooldown_time-diff)+""));
            } else {
                util.openInventory(player);
                util.sendMessage(player, true, plugin.config.getString("options.openmessage"));
                plugin.cooldown.remove(player.getName());
            }
        } 
        else 
        {
            util.openInventory(player);
            util.sendMessage(player, true, plugin.config.getString("options.openmessage"));
        }
    }
}