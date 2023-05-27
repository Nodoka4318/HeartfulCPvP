package ml.heartfulcpvp.hfcpvp.modules.surround;

import ml.heartfulcpvp.hfcpvp.math.Vec3d;
import org.bukkit.Location;
import org.bukkit.Material;
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

        if (!mod.getPlayerData().isEnabled(player)) {
            return;
        }

        var playerBlockPos = getPlayerBlockPos(player);
        var playerBlock = player.getWorld().getBlockAt(playerBlockPos);

        if (playerBlock.getType() == Material.AIR) {
            return; // プレイヤーがブロックに乗ってない
        }

        var playerBlockPosVec = new Vec3d(playerBlockPos);
        var relativePosVecs = new Vec3d[] {
                new Vec3d(1, 0, 0),
                new Vec3d(0, 0, 1),
                new Vec3d(-1, 0, 0),
                new Vec3d(0, 0, -1)
        };

        for (var relVec : relativePosVecs) {
            var placePosVec = Vec3d.addVector(playerBlockPosVec, relVec);
            // TODO:
        }
    }

    private Location getPlayerBlockPos(Player player) {
        var x = Math.floor(player.getLocation().getX()) + 0.5;
        var y = Math.floor(player.getLocation().getY()) - 1;
        var z = Math.floor(player.getLocation().getZ()) + 0.5;

        return new Location(player.getWorld(), x, y, z);
    }

}
