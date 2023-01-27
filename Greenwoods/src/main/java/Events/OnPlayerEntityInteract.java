package Events;

import GUI.Inventories.Shop;
import Greenwoods.Greenwoods;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class OnPlayerEntityInteract extends @NotNull ItemStack implements Listener {
    @EventHandler
    public void onPlayerEntityInteract(PlayerInteractEntityEvent e) {

        if (e.getRightClicked().getType() == EntityType.VILLAGER) {

            e.setCancelled(true);

            Player p = e.getPlayer();
            Villager v = (Villager) e.getRightClicked();

            HashMap<Integer, Enchantment> shopEnchantments1 = new HashMap<>();


            ArrayList<ItemStack> vSoldItems = new ArrayList<>();
            vSoldItems.add(new ItemStack(Material.ENCHANTED_BOOK, 1)); //Mending
            vSoldItems.add(new ItemStack(Material.WRITTEN_BOOK, 1)); //Job Reset Token
            vSoldItems.add(new ItemStack(Material.SPECTRAL_ARROW, 16)); //Spectral Arrow
            vSoldItems.add(new ItemStack(Material.ENCHANTED_BOOK, 1)); //OG Player

            //Tag for Job Reset Token
            ItemMeta jobResetMeta = vSoldItems.get(1).getItemMeta();
            PersistentDataContainer jobResetTagPDC = jobResetMeta.getPersistentDataContainer();
            NamespacedKey jobResetTag = new NamespacedKey(Greenwoods.getInstance(), "JobReset");
            jobResetTagPDC.set(jobResetTag, PersistentDataType.INTEGER, 1);
            vSoldItems.get(1).setItemMeta(jobResetMeta);

            //Add mending enchant to the sold mending book
            vSoldItems.set(0, Shop.enchantBook(vSoldItems.get(0), Enchantment.MENDING, 1, false)); //Mending )

            ArrayList<String> vSoldItemNames = new ArrayList<>();
            vSoldItemNames.add("Mending Book"); //Mendings
            vSoldItemNames.add("Job Reset Token"); //Job Reset Token
            vSoldItemNames.add(""); //Spectral Arrow
            vSoldItemNames.add("OG Player"); //OG Player

            ArrayList<String> vSoldItemDescriptions = new ArrayList<>();
            vSoldItemDescriptions.add("Price: ♧500"); //Mending
            vSoldItemDescriptions.add("This item can be used to change your job.#Price: ♧1500"); //Job Reset Token
            vSoldItemDescriptions.add("Price: ♧10"); //Spectral Arrow
            vSoldItemDescriptions.add("What do you expect, this is just a flex.#This item will be removed and might#be worth more in the future!#Price: ♧2000"); //OG Player

            ArrayList<Double> vSoldItemPrices = new ArrayList<>();
            vSoldItemPrices.add(500.0); //Mending
            vSoldItemPrices.add(1500.0); //Job Reset Token
            vSoldItemPrices.add(10.0); //Spectral Arrow
            vSoldItemPrices.add(2000.0); //OG Player

            ArrayList<String> vSoldItemsLoreAfterBuy = new ArrayList<>();
            vSoldItemsLoreAfterBuy.add(""); //Mending
            vSoldItemsLoreAfterBuy.add("Press right click while holding this item#to reset your job. Afterwards pick#a new job using /job select <occupation>"); //Job Reset Token
            vSoldItemsLoreAfterBuy.add(""); //Spectral Arrow
            vSoldItemsLoreAfterBuy.add("What do you expect, this is just a flex.#This item will be removed and might#be worth more in the future!"); //OG Player

            new Shop(p, vSoldItems, vSoldItemNames, vSoldItemDescriptions, vSoldItemPrices, "Villager Shop", vSoldItemsLoreAfterBuy);
        }
    }
}
