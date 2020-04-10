package com.ctooley.plugins.economy;

import java.util.logging.Level;

import com.ctooley.plugins.SpawnerShop;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;


public class ShopEconomyVault {

    public boolean enabled = false;
    private Economy vault;
    private SpawnerShop spawnerShop;

    public ShopEconomyVault(SpawnerShop spawnerShop)
    {
        this.spawnerShop = spawnerShop;
        enabled = initialize();
    }

    public boolean initialize()
    {
        try {
			RegisteredServiceProvider<Economy> provider = spawnerShop.getServer().getServicesManager().getRegistration(Economy.class);
			if (provider != null) {
                vault = provider.getProvider();
                spawnerShop.logger.Log(Level.INFO, "Successfully loaded Vault economy provider.");
				return true;
            }
        } catch (NoClassDefFoundError ex) {}
        spawnerShop.logger.Log(Level.INFO, "Vault and/or a valid economy provider not found.");
        return false;
    }

    public void deposit(Player player, int amount)
    {
        vault.depositPlayer(player, amount);
    }

    public void withdraw(Player player, int amount)
    {
        vault.withdrawPlayer(player, amount);
    }

    public double getBalance(Player player)
    {
        return vault.getBalance(player);
    }
}