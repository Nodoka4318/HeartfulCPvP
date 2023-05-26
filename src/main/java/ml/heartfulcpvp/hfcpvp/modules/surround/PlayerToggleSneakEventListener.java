package ml.heartfulcpvp.hfcpvp.modules.surround;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Vector;

public class PlayerToggleSneakEventListener implements Listener {
    private SurroundModule mod;

    public PlayerToggleSneakEventListener(SurroundModule mod) {
        this.mod = mod;
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        var player = event.getPlayer();
        if (player.isSneaking()) {
            return; // すでにスニーク状態の場合
        }

        if (!mod.getPlayerData().isEnabled(player))
            return;

        // TODO: ここにsurroundの処理
    }

    private Location getCenterOfBlock(Player player) {
        var x = Math.floor(player.getLocation().getX()) + 0.5;
        var y = Math.floor(player.getLocation().getY());
        var z = Math.floor(player.getLocation().getZ()) + 0.5;

        return new Location(player.getWorld(), x, y, z);
    }

}
