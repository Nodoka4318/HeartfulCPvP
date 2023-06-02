package ml.heartfulcpvp.hfcpvp.functions.multitask;

import ml.heartfulcpvp.hfcpvp.functions.Function;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class MultiTaskFixer extends Function {
    public MultiTaskFixer() {
        registerListener(new Listener() {
            @EventHandler
            public void onPlayerInteract(PlayerInteractEvent event) {
                var player = event.getPlayer();
            }
        });
    }
}
