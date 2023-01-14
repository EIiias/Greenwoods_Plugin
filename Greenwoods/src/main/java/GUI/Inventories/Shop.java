package GUI.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Shop {

    private Inventory shopInventory = null;
    public ArrayList<Double> itemPrices = new ArrayList<>();
    public ArrayList<ItemStack> returnItems = new ArrayList<>();

    public Shop(Player p, ArrayList<ItemStack> soldItems, ArrayList<String> soldItemNames, ArrayList<String> soldItemDescriptions, ArrayList<Double> soldItemPrices, String shopName, ArrayList<String> loreAfterBuy) {

        int slotAmount = (int) (9 * (Math.ceil(Math.abs(soldItems.size() / 9))));

        if (slotAmount <= 0) {slotAmount = 9;} else if (slotAmount >= 54) {slotAmount = 54;}

        this.shopInventory = Bukkit.createInventory(null, slotAmount, shopName);
        shopList.put(shopInventory, this);

        for (int i = 0; i < soldItems.size(); i++) {

            itemPrices = soldItemPrices;
            ItemStack returnItem = new ItemStack(soldItems.get(i));

            ArrayList<String> shopItemLore = new ArrayList<>();
            ArrayList<String> returnItemLore = new ArrayList<>();

            ItemMeta shopItemMeta = soldItems.get(i).getItemMeta();

            if (!soldItemNames.get(i).equals("")) {
                shopItemMeta.setDisplayName(soldItemNames.get(i));
            }
            if (!soldItemDescriptions.get(i).equals("")) {
                String[] lines = soldItemDescriptions.get(i).split("#");
                shopItemLore.addAll(Arrays.asList(lines));
            }
            shopItemMeta.setLore(shopItemLore);
            soldItems.get(i).setItemMeta(shopItemMeta);
            shopInventory.setItem(i, soldItems.get(i));

            if (!loreAfterBuy.get(i).equals("")) {
                String[] lines = loreAfterBuy.get(i).split("#");
                returnItemLore.addAll(Arrays.asList(lines));
            }
            shopItemMeta.setLore(returnItemLore);
            returnItem.setItemMeta(shopItemMeta);
            returnItems.add(returnItem);
        }

        if (shopInventory == null) {
            return;
        }



        p.openInventory(shopInventory);
    }

    public static ItemStack enchantBook(ItemStack item, Enchantment enchantment, int level, boolean ignoreLevelRequirement) {
        EnchantmentStorageMeta itemESM = (EnchantmentStorageMeta) item.getItemMeta();
        itemESM.addStoredEnchant(enchantment, level, ignoreLevelRequirement);
        item.setItemMeta(itemESM);
        return item;
    }

    public static HashMap<Inventory, Shop> shopList = new HashMap<>();
}



