package ml.heartfulcpvp.hfcpvp.modules.surround;

import com.comphenix.protocol.wrappers.BlockPosition;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.math.Vec3d;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

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

        if (player.getInventory().getItemInMainHand().getType() != Material.OBSIDIAN) {
            return; // 黒曜石がmainhandにない
        }

        var playerBlockPos = getPlayerBlockPos(player);
        var blockPlayerOn = player.getWorld().getBlockAt(
                (int) Math.floor(playerBlockPos.getX()),
                (int) Math.floor(playerBlockPos.getY()) - 1,
                (int) Math.floor(playerBlockPos.getX())
        );

        if (blockPlayerOn.getType() == Material.AIR) {
            return; // プレイヤーがブロックに乗ってない
        }

        var playerBlockPosVec = new Vec3d(playerBlockPos);
        var relativePosVecs = new Vec3d[] {
                new Vec3d(1, -1, 0),
                new Vec3d(1, 0, 0),
                new Vec3d(0, -1, 1),
                new Vec3d(0, 0, 1),
                new Vec3d(-1, -1, 0),
                new Vec3d(-1, 0, 0),
                new Vec3d(0, -1, -1),
                new Vec3d(0, 0, -1),
        };


        for (int i = 0; i < relativePosVecs.length; i++) {
            var relVec = relativePosVecs[i];
            var placePosVec = Vec3d.addVector(playerBlockPosVec, relVec);
            var placePosBlock = player.getWorld().getBlockAt(placePosVec.toLocation(player.getWorld()));

            if (placePosBlock.getType() != Material.AIR) {
                continue; // すでにそこにブロックある
            } else {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        SurroundModule.placeBlock(player, placePosVec);
                    }
                }.runTaskLater(Plugin.getInstance(), 2 * i);
            }
        }
    }

    private Location getPlayerBlockPos(Player player) {
        var x = Math.floor(player.getLocation().getX()) + 0.5;
        var y = Math.floor(player.getLocation().getY());
        var z = Math.floor(player.getLocation().getZ()) + 0.5;

        return new Location(player.getWorld(), x, y, z);
    }
}
