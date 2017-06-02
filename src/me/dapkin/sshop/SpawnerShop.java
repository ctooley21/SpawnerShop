package me.dapkin.sshop;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class SpawnerShop extends JavaPlugin {
    static Economy economy = null;
    Inventory spawnerInv = Bukkit.createInventory(null, getConfig().getInt("options.inventorysize"), getConfig().getString("options.shopname"));
    HashMap<String, Long> cooldown = new HashMap<>();
    FileConfiguration config = getConfig();

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new ShopSigns(this), this);
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

    void setupInv() {
        ItemStack mushroom = new ItemStack(Material.MONSTER_EGG, 1, (short) 96);
        ItemStack sheep = new ItemStack(Material.MONSTER_EGG, 1, (short) 91);
        ItemStack ocelot = new ItemStack(Material.MONSTER_EGG, 1, (short) 98);
        ItemStack pig = new ItemStack(Material.MONSTER_EGG, 1, (short) 90);
        ItemStack cow = new ItemStack(Material.MONSTER_EGG, 1, (short) 92);
        ItemStack chicken = new ItemStack(Material.MONSTER_EGG, 1, (short) 93);
        ItemStack wolf = new ItemStack(Material.MONSTER_EGG, 1, (short) 95);
        ItemStack spider = new ItemStack(Material.MONSTER_EGG, 1, (short) 52);
        ItemStack cspider = new ItemStack(Material.MONSTER_EGG, 1, (short) 59);
        ItemStack creeper = new ItemStack(Material.MONSTER_EGG, 1, (short) 50);
        ItemStack zombie = new ItemStack(Material.MONSTER_EGG, 1, (short) 54);
        ItemStack skeleton = new ItemStack(Material.MONSTER_EGG, 1, (short) 51);
        ItemStack blaze = new ItemStack(Material.MONSTER_EGG, 1, (short) 61);
        ItemStack enderman = new ItemStack(Material.MONSTER_EGG, 1, (short) 58);
        ItemStack bat = new ItemStack(Material.MONSTER_EGG, 1, (short) 65);
        ItemStack rabbit = new ItemStack(Material.MONSTER_EGG, 1, (short) 101);
        ItemStack squid = new ItemStack(Material.MONSTER_EGG, 1, (short) 94);
        ItemStack villager = new ItemStack(Material.MONSTER_EGG, 1, (short) 120);
        ItemStack zpig = new ItemStack(Material.MONSTER_EGG, 1, (short) 57);
        ItemStack sfish = new ItemStack(Material.MONSTER_EGG, 1, (short) 60);
        ItemStack igolem = new ItemStack(Material.MONSTER_EGG, 1, (short) 99);
        ItemStack slime = new ItemStack(Material.MONSTER_EGG, 1, (short) 55);
        ItemStack horse = new ItemStack(Material.MONSTER_EGG, 1, (short) 100);
        ItemStack witch = new ItemStack(Material.MONSTER_EGG, 1, (short) 66);

        if (config.getBoolean("spawners.enderman")) {
            ItemMeta ienderman = enderman.getItemMeta();
            ienderman.setDisplayName(ChatColor.WHITE + "Enderman Spawner");
            ienderman.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.enderman"))}));
            enderman.setItemMeta(ienderman);
            spawnerInv.addItem(enderman);
        }
        if (config.getBoolean("spawners.blaze")) {
            ItemMeta iblaze = blaze.getItemMeta();
            iblaze.setDisplayName(ChatColor.WHITE + "Blaze Spawner");
            iblaze.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.blaze"))}));
            blaze.setItemMeta(iblaze);
            spawnerInv.addItem(blaze);
        }
        if (config.getBoolean("spawners.skeleton")) {
            ItemMeta iskeleton = skeleton.getItemMeta();
            iskeleton.setDisplayName(ChatColor.WHITE + "Skeleton Spawner");
            iskeleton.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.skeleton"))}));
            skeleton.setItemMeta(iskeleton);
            spawnerInv.addItem(skeleton);
        }
        if (config.getBoolean("spawners.zombie")) {
            ItemMeta izombie = zombie.getItemMeta();
            izombie.setDisplayName(ChatColor.WHITE + "Zombie Spawner");
            izombie.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.zombie"))}));
            zombie.setItemMeta(izombie);
            spawnerInv.addItem(zombie);
        }
        if (config.getBoolean("spawners.creeper")) {
            ItemMeta icreeper = creeper.getItemMeta();
            icreeper.setDisplayName(ChatColor.WHITE + "Creeper Spawner");
            icreeper.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.creeper"))}));
            creeper.setItemMeta(icreeper);
            spawnerInv.addItem(creeper);
        }
        if (config.getBoolean("spawners.cavespider")) {
            ItemMeta icspider = cspider.getItemMeta();
            icspider.setDisplayName(ChatColor.WHITE + "Cave Spider Spawner");
            icspider.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.cavespider"))}));
            cspider.setItemMeta(icspider);
            spawnerInv.addItem(cspider);
        }
        if (config.getBoolean("spawners.spider")) {
            ItemMeta ispider = spider.getItemMeta();
            ispider.setDisplayName(ChatColor.WHITE + "Spider Spawner");
            ispider.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.spider"))}));
            spider.setItemMeta(ispider);
            spawnerInv.addItem(spider);
        }
        if (config.getBoolean("spawners.wolf")) {
            ItemMeta iwolf = wolf.getItemMeta();
            iwolf.setDisplayName(ChatColor.WHITE + "Wolf Spawner");
            iwolf.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysing") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.wolf"))}));
            wolf.setItemMeta(iwolf);
            spawnerInv.addItem(wolf);
        }
        if (config.getBoolean("spawners.chicken")) {
            ItemMeta ichicken = chicken.getItemMeta();
            ichicken.setDisplayName(ChatColor.WHITE + "Chicken Spawner");
            ichicken.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.chicken"))}));
            chicken.setItemMeta(ichicken);
            spawnerInv.addItem(chicken);
        }
        if (config.getBoolean("spawners.cow")) {
            ItemMeta icow = cow.getItemMeta();
            icow.setDisplayName(ChatColor.WHITE + "Cow Spawner");
            icow.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.cow"))}));
            cow.setItemMeta(icow);
            spawnerInv.addItem(cow);
        }
        if (config.getBoolean("spawners.pig")) {
            ItemMeta ipig = pig.getItemMeta();
            ipig.setDisplayName(ChatColor.WHITE + "Pig Spawner");
            ipig.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.pig"))}));
            pig.setItemMeta(ipig);
            spawnerInv.addItem(pig);
        }
        if (config.getBoolean("spawners.ocelot")) {
            ItemMeta iocelot = ocelot.getItemMeta();
            iocelot.setDisplayName(ChatColor.WHITE + "Ocelot Spawner");
            iocelot.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.ocelot"))}));
            ocelot.setItemMeta(iocelot);
            spawnerInv.addItem(ocelot);
        }
        if (config.getBoolean("spawners.mushroom")) {
            ItemMeta imushroom = mushroom.getItemMeta();
            imushroom.setDisplayName(ChatColor.WHITE + "Mushroom Cow Spawner");
            imushroom.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.mushroom"))}));
            mushroom.setItemMeta(imushroom);
            spawnerInv.addItem(mushroom);
        }
        if (config.getBoolean("spawners.sheep")) {
            ItemMeta isheep = sheep.getItemMeta();
            isheep.setDisplayName(ChatColor.WHITE + "Sheep Spawner");
            isheep.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.sheep"))}));
            sheep.setItemMeta(isheep);
            spawnerInv.addItem(sheep);
        }
        if (config.getBoolean("spawners.bat")) {
            ItemMeta ibat = bat.getItemMeta();
            ibat.setDisplayName(ChatColor.WHITE + "Bat Spawner");
            ibat.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.bat"))}));
            bat.setItemMeta(ibat);
            spawnerInv.addItem(bat);
        }
        if (config.getBoolean("spawners.rabbit")) {
            ItemMeta irabbit = rabbit.getItemMeta();
            irabbit.setDisplayName(ChatColor.WHITE + "Rabbit Spawner");
            irabbit.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.rabbit"))}));
            rabbit.setItemMeta(irabbit);
            spawnerInv.addItem(rabbit);
        }
        if (config.getBoolean("spawners.squid")) {
            ItemMeta isquid = squid.getItemMeta();
            isquid.setDisplayName(ChatColor.WHITE + "Squid Spawner");
            isquid.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.squid"))}));
            squid.setItemMeta(isquid);
            spawnerInv.addItem(squid);
        }
        if (config.getBoolean("spawners.villager")) {
            ItemMeta ivillager = villager.getItemMeta();
            ivillager.setDisplayName(ChatColor.WHITE + "Villager Spawner");
            ivillager.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.villager"))}));
            villager.setItemMeta(ivillager);
            spawnerInv.addItem(villager);
        }
        if (config.getBoolean("spawners.zombiepigman")) {
            ItemMeta izpig = zpig.getItemMeta();
            izpig.setDisplayName(ChatColor.WHITE + "Zombie Pigman Spawner");
            izpig.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.zombiepig"))}));
            zpig.setItemMeta(izpig);
            spawnerInv.addItem(zpig);
        }
        if (config.getBoolean("spawners.silverfish")) {
            ItemMeta isfish = sfish.getItemMeta();
            isfish.setDisplayName(ChatColor.WHITE + "Silverfish Spawner");
            isfish.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.silverfish"))}));
            sfish.setItemMeta(isfish);
            spawnerInv.addItem(sfish);
        }
        if (config.getBoolean("spawners.irongolem")) {
            ItemMeta iigolem = igolem.getItemMeta();
            iigolem.setDisplayName(ChatColor.WHITE + "Iron Golem Spawner");
            iigolem.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.irongolem"))}));
            igolem.setItemMeta(iigolem);
            spawnerInv.addItem(igolem);
        }
        if (config.getBoolean("spawners.slime")) {
            ItemMeta islime = slime.getItemMeta();
            islime.setDisplayName(ChatColor.WHITE + "Slime Spawner");
            islime.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.slime"))}));
            slime.setItemMeta(islime);
            spawnerInv.addItem(slime);
        }
        if (config.getBoolean("spawners.horse")) {
            ItemMeta ihorse = horse.getItemMeta();
            ihorse.setDisplayName(ChatColor.WHITE + "Horse Spawner");
            ihorse.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.horse"))}));
            horse.setItemMeta(ihorse);
            spawnerInv.addItem(horse);
        }
        if (config.getBoolean("spawners.witch")) {
            ItemMeta iwitch = witch.getItemMeta();
            iwitch.setDisplayName(ChatColor.WHITE + "Witch Spawner");
            iwitch.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.witch"))}));
            witch.setItemMeta(iwitch);
            spawnerInv.addItem(witch);
        }
        if (config.getBoolean("spawners.magmacube")) {
            ItemMeta iwitch = witch.getItemMeta();
            iwitch.setDisplayName(ChatColor.WHITE + "Magma Cube Spawner");
            iwitch.setLore(Arrays.asList(new String[]{ChatColor.GREEN + "Price: " + config.getString("options.currencysign") + NumberFormat.getNumberInstance(Locale.US).format(getConfig().getInt("prices.witch"))}));
            witch.setItemMeta(iwitch);
            spawnerInv.addItem(witch);
        }
    }

    void giveSpawner(Player p, String mob) {
        ItemStack mobSpawner = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta mobMeta = mobSpawner.getItemMeta();
        mobMeta.setDisplayName(ChatColor.WHITE + capFirst(mob) + " Spawner");
        mobSpawner.setItemMeta(mobMeta);
        p.getInventory().addItem(mobSpawner);
    }

    private String capFirst(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    void updateSpawner(Block block, String type) {
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
                    case "Cave Spider":
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
                    case "Mushroom Cow":
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
                    case "Zombie Pigman":
                        spawner.setSpawnedType(EntityType.PIG_ZOMBIE);
                        break;
                    case "Silverfish":
                        spawner.setSpawnedType(EntityType.SILVERFISH);
                        break;
                    case "Iron Golem":
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
                }
                spawner.update();
            }
        }, 1L);
    }
}