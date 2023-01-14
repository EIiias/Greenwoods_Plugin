package Timer;


import Greenwoods.Greenwoods;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer  {

        private boolean running; // true or false
        public static int time;

        public Timer(boolean running, int time) {
                this.running = running;
                Timer.time = time;

                run();
        }

        public boolean isRunning() {
                return running;
        }

        public void setRunning(boolean running) {
                this.running = running;
        }

        public int getTime() {
                return time;
        }

        public void setTime(int time) {
                this.time = time;
        }

        public void sendActionBar() {

                for (Player player : Bukkit.getOnlinePlayers()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + "Countdown: " + getTime()));
                }
        }

        private void run () {
                new BukkitRunnable() {
                        @Override
                        public void run () {
                                if (!isRunning()) {
                                        return;
                                }

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                        p.setInvulnerable(true);
                                }

                                time = time - 1;

                                sendActionBar();

                                if (time <= 0) {
                                        time = 0;
                                        for (Player p : Bukkit.getOnlinePlayers()) {
                                                p.setInvulnerable(false);
                                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Es geht los!"));
                                        }
                                        setRunning(false);
                                }
                        }
                }.runTaskTimer(Greenwoods.getInstance(), 20, 20);

        }
}
