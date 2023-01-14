package Events;

import GUI.Inventories.Shop;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OnInventoryClose implements Listener {
    @EventHandler
    private void onInventoryClose(InventoryCloseEvent e) {

        if (Shop.shopList.containsKey(e.getInventory())) {
            Shop shopInstance = Shop.shopList.get(e.getInventory());
            Shop.shopList.remove(e.getInventory());
            shopInstance = null;
        }
    }
}
