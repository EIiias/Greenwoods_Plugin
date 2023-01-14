package Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InvisibleitemframesCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sendUsage(sender);
            return true;
        }

        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].toLowerCase();
        }

        switch (args.length) {
            case 1 -> {
                switch (args[0]) {
                    case "true" -> {
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            ItemStack mainHandItem = p.getInventory().getItemInMainHand();
                            if (mainHandItem.getType() == Material.ITEM_FRAME) {
                                p.getInventory().removeItem(mainHandItem);
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + p.getName() + " item_frame " + mainHandItem.getAmount() + " {EntityTag:{Invisible:1}}");
                                sender.sendMessage(ChatColor.GREEN + "The item frames should now be invisible!");
                            } else {
                                sender.sendMessage(ChatColor.RED + "The item frames you want to convert need to be in your main hand!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "This command may only be executed by players!");
                        }
                    }
                    case "false" -> {
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            ItemStack mainHandItem = p.getInventory().getItemInMainHand();
                            if (mainHandItem.getType() == Material.ITEM_FRAME) {
                                p.getInventory().removeItem(mainHandItem);
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + p.getName() + " item_frame " + mainHandItem.getAmount());
                                sender.sendMessage(ChatColor.GREEN + "The item frames should now be visible!");
                            } else {
                                sender.sendMessage(ChatColor.RED + "The item frames you want to convert need to be in your main hand!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "This command may only be executed by players!");
                        }
                    }
                    case "help" -> {
                        sendUsage(sender);
                    }
                }
            }
            default -> {
                sendUsage(sender);
            }
        }

        return true;
    }

    private void sendUsage(CommandSender s) {

        if (s instanceof Player) {
            s.sendMessage(ChatColor.YELLOW + "/invisbleitemframes help - shows all options for this command");
            s.sendMessage(ChatColor.YELLOW + "/invisbleitemframes true - makes item frames in your main hand invisible");
            s.sendMessage(ChatColor.YELLOW + "/invisbleitemframes false - makes item frames in your main hand visible");
        }

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ArrayList<String> argSuggestions = new ArrayList<>();
        ArrayList<String> argsToRemove = new ArrayList<>();

        switch (args.length) {
            case 1 -> {

                argSuggestions.add("help");
                argSuggestions.add("true");
                argSuggestions.add("false");

                for (String argSuggestion : argSuggestions) {
                    if (!argSuggestion.startsWith(args[0])) {
                        argsToRemove.add(argSuggestion);
                    }
                }
            }
        }

        for (String argToRemove : argsToRemove) {
            argSuggestions.remove(argToRemove);
        }

        return argSuggestions;
    }
}
