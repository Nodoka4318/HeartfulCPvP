package ml.heartfulcpvp.hfcpvp.modules.norender;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class PlayerResourcePackStatusEventListener implements Listener {
    private NoRenderModule mod;

    public PlayerResourcePackStatusEventListener(NoRenderModule mod) {
        this.mod = mod;
    }

    @EventHandler
    public void onPlayerUpdateResourcePack(PlayerResourcePackStatusEvent event) {
        event.getPlayer().sendMessage("ロードされとるよ〜");
    }
}
