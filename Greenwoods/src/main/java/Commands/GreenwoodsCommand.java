package Commands;

import Events.OnPlayerJoin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GreenwoodsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.getInventory().addItem(OnPlayerJoin.getManual());
            }
        } else {
            sendUsage(sender);
        }
        return true;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "Usage: /greenwoods - tells you what you can do");
    }
}
