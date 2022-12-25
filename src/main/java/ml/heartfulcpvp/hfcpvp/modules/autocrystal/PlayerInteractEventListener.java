package ml.heartfulcpvp.hfcpvp.modules.autocrystal;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import ml.heartfulcpvp.hfcpvp.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
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
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        // TODO: PlayerData参照
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
                            entity.remove();
                            e.setCancelled(true);
                            return;
                        }

                        var below = entity.getLocation().getBlock().getRelative(BlockFace.DOWN);
                        if (e.getClickedBlock().equals(below)) {

                            PacketContainer packet = new PacketContainer(PacketType.Play.Client.USE_ENTITY);

                            packet.getEntityUseActions().write(0, EnumWrappers.EntityUseAction.ATTACK);
                            packet.getModifier().write(0, entity.getEntityId());
                            try {
                                Plugin.getProtocolManager().recieveClientPacket(e.getPlayer(), packet);
                            } catch (IllegalAccessException | InvocationTargetException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }, 0);
        }
    }
}
