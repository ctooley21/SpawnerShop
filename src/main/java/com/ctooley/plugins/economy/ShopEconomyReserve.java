package com.ctooley.plugins.economy;

import java.math.BigDecimal;
import java.util.logging.Level;

import com.ctooley.plugins.SpawnerShop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.tnemc.core.Reserve;
import net.tnemc.core.economy.EconomyAPI;

public class ShopEconomyReserve {

    public boolean enabled = false;
    private EconomyAPI reserve;

    public ShopEconomyReserve()
    {
        enabled = initialize();
    }

    public boolean initialize()
    {
        reserve = Reserve.instance().economy();
        if(reserve != null)
        {
            //successfully loaded reserve economy provider
            return true;
        }
        //reserve is installed, but no valid economy provider.
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