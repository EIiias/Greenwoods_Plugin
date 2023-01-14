package Events;


import Timer.Timer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove (PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (p.isOp()){
            return;
        }
        if (Timer.time > 0) {
            if (event.getFrom() != event.getTo()) {
                event.setCancelled(true);
            }
        }
    }
}
