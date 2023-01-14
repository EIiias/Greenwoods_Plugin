package Commands;

import Greenwoods.Greenwoods;
import Timer.Timer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CountdownCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {

            sendUsage(sender);
            return true;
        }

        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].toLowerCase();
        }

        switch (args[0]) {
            case "resume": {
                Timer timer = Greenwoods.timerInstance;

                if (timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Der Timer läuft bereits.");
                    break;
                }

                timer.setRunning(true);
                sender.sendMessage("Der Timer wurde gestartet.");
                break;
            }
            case "pause": {
                Timer timer = Greenwoods.timerInstance;

                if (!timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Der Timer läuft nicht.");
                    break;
                }

                timer.setRunning(false);
                sender.sendMessage("Der Timer wurde gestoppt.");
                break;
            }
            case "set":{
                if(args.length != 2) {
                    sender.sendMessage("Verwendung: /timer time <Zeit>");
                    return true;
                }

                try {
                    Timer timer = Greenwoods.timerInstance;

                    timer.setRunning(false);
                    timer.setTime(Integer.parseInt(args[1]));
                    sender.sendMessage("Die Zeit wurde auf " + args[1] + " gesetzt");
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Dein Parameter 2 muss eine Zahl sein.");
                }
                break;
            }

            case "reset":
            {
                Timer timer = Greenwoods.timerInstance;

                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage("Der Timer wurde zurückgesetzt");
                break;
            }

            default:
                sendUsage(sender);
                break;
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage("Verwendung: /countdown resume, /countdown pause, /countdown set <Zeit>, /coutdown reset");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ArrayList<String> argSuggestions = new ArrayList<>();

        switch (args.length) {
            case 1 -> {
                argSuggestions.add("set");
                argSuggestions.add("pause");
                argSuggestions.add("resume");
                argSuggestions.add("reset");
            }
        }

        return argSuggestions;
    }
}

