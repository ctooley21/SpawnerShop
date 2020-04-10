package com.ctooley.plugins.economy;

import java.math.BigDecimal;
import java.util.logging.Level;

import com.ctooley.plugins.SpawnerShop;

import org.bukkit.entity.Player;

import net.tnemc.core.Reserve;
import net.tnemc.core.economy.EconomyAPI;

public class ShopEconomyReserve {

    private SpawnerShop spawnerShop;
    public boolean enabled = false;
    private EconomyAPI reserve;

    public ShopEconomyReserve(SpawnerShop spawnerShop)
    {
        this.spawnerShop = spawnerShop;
        enabled = initialize();
    }

    public boolean initialize()
    {
        reserve = Reserve.instance().economy();
        if(reserve != null)
        {
            //successfully loaded reserve economy provider
            spawnerShop.logger.Log(Level.INFO, "Successfully loaded Reserve economy provider.");
            return true;
        }
        //reserve is installed, but no valid economy provider.
        spawnerShop.logger.Log(Level.INFO, "Reserve found, but no valid Reserve economy provider.");
        return false;
    }

    public void deposit(Player player, int amount)
    {
        reserve.addHoldings(player.getUniqueId(), new BigDecimal(amount));
    }

    public void withdraw(Player player, int amount)
    {
        reserve.removeHoldings(player.getUniqueId(), new BigDecimal(amount));
    }

    public double getBalance(Player player)
    {
        return reserve.getHoldings(player.getUniqueId()).doubleValue();
    }
}