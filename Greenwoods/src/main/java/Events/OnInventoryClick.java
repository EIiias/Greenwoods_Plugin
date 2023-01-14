package Events;

import Economy.Vault;
import GUI.Inventories.Shop;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

public class OnInventoryClick implements Listener {
    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {

        if (Shop.shopList.containsKey(e.getInventory())) {
            Player p = (Player) e.getWhoClicked();
            Shop shopInstance = Shop.shopList.get(e.getInventory());
            Economy economy = Vault.getEconomy();

            e.setCancelled(true);

            if (e.getClickedInventory() instanceof PlayerInventory) {
                return;
            }

            if (e.getCurrentItem() != null) {

                if (e.getSlot() > shopInstance.itemPrices.size() - 1) {
                    return;
                }

                if (shopInstance.itemPrices.get(e.getSlot()) <= economy.getBalance(p))  {
                    if (p.getInventory().firstEmpty() != -1) {
                        economy.withdrawPlayer(p, shopInstance.itemPrices.get(e.getSlot()));
                        p.getInventory().addItem(shopInstance.returnItems.get(e.getSlot()));
                    } else {
                        p.sendMessage(ChatColor.RED + "You have no space in your inventory!!");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You need more money!");
                }
                e.setCancelled(true);
            }
        }
    }
}
