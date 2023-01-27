package Events;

import org.bukkit.Bukkit;
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
            p.sendMessage(ChatColor.GREEN+ "Willkommen, " + p.getName() + " auf " + ChatColor.GOLD + "Greenwoods.at!");
            p.sendMessage(ChatColor.GREEN + "Viel Spaß und viel Erfolg!");
            p.getInventory().addItem(getManual());
            p.sendMessage(ChatColor.GREEN + "Das Spielerhandbuch kannst du jederzeit mittels /Greenwoods wieder erhalten");
            e.setJoinMessage(ChatColor.GREEN + p.getName() + ChatColor.WHITE + " wurde an Ufer gespült...");
            p.chat("/crawl");
        }
    }

    //Buch für Anfänger
    public static ItemStack getManual() {
        ItemStack manual = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta manualMeta = (BookMeta) manual.getItemMeta();


    //Author/Titel/Inhaltsverzeichnis
        manualMeta.setAuthor(
                ChatColor.GOLD + "Greenwoods"
        );

        manualMeta.setTitle(
                ChatColor.GOLD + "Spieler Handbuch / How to play"
        );

        manualMeta.addPage(
                ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Inhaltsverzeichnis\n\n" + ChatColor.RESET +
                "-Chunk claimen S2-S3\n-Jobs plugin S2-S3"
        );

    //Chunks
        manualMeta.addPage(
                ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Chunks claimen:\n\n" + ChatColor.RESET +
                "Mit " + ChatColor.BOLD + "/chunk claim" + ChatColor.RESET + " kannst du einen Chunk claimen. " +
                "Dieser Chunk ist dann dein Gründstück auf welchem nur du Rechte besitzt. " +
                "Der erste Chunk ist Gratis, jeder weitere kostet dich 1,500 Woodcoins. "
        );

        manualMeta.addPage(
                "In dem von dir erworbenen Chunk kann niemand außer dir Blöcke abbauen oder Kisten öffnen. " +
                "Um einem anderen Spieler Berechtigungen an deinem Grunstück erteilen oder zu entehmen, " +
                "gibst du folgenden Befehl ein: " +
                ChatColor.BOLD + "/chunk access [Playername]" + ChatColor.RESET
        );

    // Jobs
        manualMeta.addPage(
                ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Jobs:\n\n" + ChatColor.RESET +
                "Mit dem Befehl\n" + ChatColor.BOLD +  "/job list " + ChatColor.RESET + ",listest du alle Jobs auf. " +
                "Anschließend kannst du mit\n" + ChatColor.BOLD + "/job select [Job] " + ChatColor.RESET +
                "dir einen Job auswählen. " + "Mit Hilfe der Jobs kannst du Geld verdienen (Woodcoins)"
        );

        manualMeta.addPage(
                "Falls du deinen Job wechseln möchtest," +
                "kannst du dir bei einem Dorfbewohner einen Token zum Job wechsel kaufen. "
        );

    //Invisible Itemframes
        manualMeta.addPage(
                "[In Progress]"
        );


        manual.setItemMeta(manualMeta);

        return manual;
    }
}
