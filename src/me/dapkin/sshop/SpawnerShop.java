package me.dapkin.sshop;

import me.dapkin.sshop.commands.Commands;
import me.dapkin.sshop.listeners.ShopListeners;
import me.dapkin.sshop.listeners.SignListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import java.text.NumberFormat;
import java.util.*;

public class SpawnerShop extends JavaPlugin {

    public static Economy economy = null;
    public Inventory spawnerInv = Bukkit.createInventory(null, getConfig().getInt("options.inventorysize"), getConfig().getString("options.shopname"));
    public HashMap<String, Long> cooldown = new HashMap<>();
    public FileConfiguration config = getConfig();

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new SignListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ShopListeners(this), this);
        initialiseConfig();
        getCommand("spawners").setExecutor(new Commands(this));
        setupEconomy();
        setupInv();
    }

    private void initialiseConfig() {
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    public void onDisable() {

    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return economy != null;
    }

    public void setupInv() {
        ConfigurationSection spawnerSection = config.getConfigurationSection("spawners");

        for(String spawnerKey : spawnerSection.getKeys(false)) {
            if(!config.getBoolean("spawners." + spawnerKey + ".enabled")) continue;
            short data = (short) config.getInt("spawners." + spawnerKey + ".data-value");
            ItemStack spawner = new ItemStack(Material.MONSTER_EGG, 1, data);
            ItemMeta spawnerMeta = spawner.getItemMeta();
            spawnerMeta.setDisplayName(ChatColor.WHITE + capFirst(spawnerKey) + " Spawner");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Price: " +
                    config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("spawners." + spawnerKey + ".buy-price")));
            spawnerMeta.setLore(lore);
            spawner.setItemMeta(spawnerMeta);
            spawnerInv.addItem(spawner);
        }
    }

    public void giveSpawner(Player p, String mob) {
        ItemStack mobSpawner = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta mobMeta = mobSpawner.getItemMeta();
        mobMeta.setDisplayName(ChatColor.WHITE + capFirst(mob) + " Spawner");
        mobSpawner.setItemMeta(mobMeta);
        p.getInventory().addItem(mobSpawner);
    }

    private String capFirst(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    public void updateSpawner(Block block, String type) {
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if(!(block.getState() instanceof CreatureSpawner)) return;
                CreatureSpawner spawner = (CreatureSpawner) block.getState();
                switch (type) {
                    case "Enderman":
                        spawner.setSpawnedType(EntityType.ENDERMAN);
                        break;
                    case "Blaze":
                        spawner.setSpawnedType(EntityType.BLAZE);
                        break;
                    case "Skeleton":
                        spawner.setSpawnedType(EntityType.SKELETON);
                        break;
                    case "Zombie":
                        spawner.setSpawnedType(EntityType.ZOMBIE);
                        break;
                    case "Creeper":
                        spawner.setSpawnedType(EntityType.CREEPER);
                        break;
                    case "CaveSpider":
                        spawner.setSpawnedType(EntityType.CAVE_SPIDER);
                        break;
                    case "Spider":
                        spawner.setSpawnedType(EntityType.SPIDER);
                        break;
                    case "Wolf":
                        spawner.setSpawnedType(EntityType.WOLF);
                        break;
                    case "Chicken":
                        spawner.setSpawnedType(EntityType.CHICKEN);
                        break;
                    case "Cow":
                        spawner.setSpawnedType(EntityType.COW);
                        break;
                    case "Pig":
                        spawner.setSpawnedType(EntityType.PIG);
                        break;
                    case "Ocelot":
                        spawner.setSpawnedType(EntityType.OCELOT);
                        break;
                    case "MushroomCow":
                        spawner.setSpawnedType(EntityType.MUSHROOM_COW);
                        break;
                    case "Sheep":
                        spawner.setSpawnedType(EntityType.SHEEP);
                        break;
                    case "Bat":
                        spawner.setSpawnedType(EntityType.BAT);
                        break;
                    case "Rabbit":
                        spawner.setSpawnedType(EntityType.RABBIT);
                        break;
                    case "Squid":
                        spawner.setSpawnedType(EntityType.SQUID);
                        break;
                    case "Villager":
                        spawner.setSpawnedType(EntityType.VILLAGER);
                        break;
                    case "ZombiePigman":
                        spawner.setSpawnedType(EntityType.PIG_ZOMBIE);
                        break;
                    case "Silverfish":
                        spawner.setSpawnedType(EntityType.SILVERFISH);
                        break;
                    case "IronGolem":
                        spawner.setSpawnedType(EntityType.IRON_GOLEM);
                        break;
                    case "Slime":
                        spawner.setSpawnedType(EntityType.SLIME);
                        break;
                    case "Horse":
                        spawner.setSpawnedType(EntityType.HORSE);
                        break;
                    case "Witch":
                        spawner.setSpawnedType(EntityType.WITCH);
                        break;
                    case "MagmaCube":
                        spawner.setSpawnedType(EntityType.MAGMA_CUBE);
                        break;
                    case "PolarBear":
                        spawner.setSpawnedType(EntityType.POLAR_BEAR);
                        break;
                    case "Endermite":
                        spawner.setSpawnedType(EntityType.ENDERMITE);
                        break;
                    case "Evoker":
                        spawner.setSpawnedType(EntityType.EVOKER);
                        break;
                    case "Guardian":
                        spawner.setSpawnedType(EntityType.GUARDIAN);
                        break;
                    case "Shulker":
                        spawner.setSpawnedType(EntityType.SHULKER);
                        break;
                    case "Husk":
                        spawner.setSpawnedType(EntityType.HUSK);
                        break;
                    case "Stray":
                        spawner.setSpawnedType(EntityType.STRAY);
                        break;
                    case "Vex":
                        spawner.setSpawnedType(EntityType.VEX);
                        break;
                    case "Vindicator":
                        spawner.setSpawnedType(EntityType.VINDICATOR);
                        break;
                    case "Llama":
                        spawner.setSpawnedType(EntityType.LLAMA);
                        break;
                    case "Mule":
                        spawner.setSpawnedType(EntityType.MULE);
                        break;
                }
                spawner.update();
            }
        }, 1L);
    }
}