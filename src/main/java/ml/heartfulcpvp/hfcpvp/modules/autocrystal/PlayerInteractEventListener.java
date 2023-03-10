package ml.heartfulcpvp.hfcpvp.modules.autocrystal;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import ml.heartfulcpvp.hfcpvp.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class PlayerInteractEventListener implements Listener {
    private AutoCrystalModule mod;

    public PlayerInteractEventListener(AutoCrystalModule mod) {
        this.mod = mod;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!mod.getPlayerData().isEnabled(e.getPlayer()))
            return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            var block = Objects.requireNonNull(e.getClickedBlock()).getType();
            if (!(block == Material.OBSIDIAN || block == Material.BEDROCK))
                return;

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getPlugin(), () -> {
                List<Entity> entities = e.getPlayer().getNearbyEntities(4, 4, 3);

                for (Entity entity : entities) {
                    if (entity.getType() == EntityType.ENDER_CRYSTAL) {
                        var top = entity.getLocation().getBlock().getRelative(BlockFace.UP);
                        if (top.getType() != Material.AIR) {
                            // ml.heartfulcpvp.hfcpvp.functions.crystalfixer.CrystalFixer
                            return;
                        }

                        var below = entity.getLocation().getBlock().getRelative(BlockFace.DOWN);
                        if (e.getClickedBlock().equals(below)) {
                            AutoCrystalModule.breakCrystal(e.getPlayer(), (EnderCrystal) entity);
                        }
                    }
                }
            }, 0);
        }
    }
}
