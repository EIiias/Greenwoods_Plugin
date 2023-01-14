package Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class OnPlayerJoin implements Listener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GREEN + p.getName() + ChatColor.WHITE + " ist aufgewacht!");
            return;
        }else {
            p.sendMessage(ChatColor.GREEN+ "Willkommen, " + p.getName() + " auf " + ChatColor.GOLD+ "Greenwoods.at!");
            p.sendMessage(ChatColor.GREEN + "Viel Spaß und viel Erfolg!");
            p.getInventory().addItem(getManual());
            p.sendMessage(ChatColor.GREEN + "Das Spielerhandbuch kannst du jederzeit mittels /Greenwoods wieder erhalten");
            e.setJoinMessage(ChatColor.GREEN + p.getName() + ChatColor.WHITE + " wurde an Ufer gespült...");
        }
    }

    public static ItemStack getManual() {
        ItemStack manual = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta manualMeta = (BookMeta) manual.getItemMeta();

        manualMeta.setAuthor(ChatColor.GOLD + "Greenwoods");
        manualMeta.setTitle(ChatColor.GOLD + "Spieler Handbuch / How to play");
        manualMeta.addPage(ChatColor.BOLD.toString() + ChatColor.DARK_PURPLE + "Index\n\n" + ChatColor.RESET + ChatColor.BLACK + "Chunk claimen S2-S3\nJobs plugin S2-S3");
        manualMeta.addPage( ChatColor.BOLD.toString() + ChatColor.DARK_PURPLE + "Chunk claimen:\n\n" + ChatColor.RESET + ChatColor.BLACK + "Mit /chunkclaim kannst du einen Chunk claimen " +
                "Der erste ist Gratis jeder weitere kostet dich 1,500 Woodcoins " +
                "In diesem gecliamten Chunk kann niemand außer du Blöcke abbauen oder Kisten öffnen. " +
                "Falls du einem anderen Spieler diese Dinge erlauben willst kannst du das einfach mit" +
                " /chunk access [Playername] tun.");
        manualMeta.addPage("Falls du einem anderen Spieler diese Dinge erlauben willst kannst du das einfach mit" +
                " /chunk access [Playername] tun. Sobald du den Command nochmal ausführst werden die Rechte entfernt");
        manualMeta.addPage("");

        manual.setItemMeta(manualMeta);

        return manual;
    }
}
