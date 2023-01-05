package ml.heartfulcpvp.hfcpvp.functions.crystalfixer;

import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.functions.Function;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.Objects;

public class CrystalFixer extends Function {
    public CrystalFixer() {
        registerListener(new Listener() {
            @EventHandler
            public void onPlayerInteract(PlayerInteractEvent e) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    var block = Objects.requireNonNull(e.getClickedBlock()).getType();
                    if (!(block == Material.OBSIDIAN || block == Material.BEDROCK))
                        return;

                    int amount = -1;
                    try {
                        amount = e.getPlayer().getItemInUse().getAmount();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    int finalAmount = amount;
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getPlugin(), () -> {
                        List<Entity> entities = e.getPlayer().getNearbyEntities(4, 4, 3);

                        for (Entity entity : entities) {
                            if (entity.getType() == EntityType.ENDER_CRYSTAL) {
                                var top = entity.getLocation().getBlock().getRelative(BlockFace.UP);
                                if (top.getType() != Material.AIR) {
                                    entity.remove();
                                    var player = e.getPlayer();
                                    if (finalAmount != -1) {
                                        player.getItemInUse().setAmount(finalAmount);
                                    }

                                    e.setCancelled(true);
                                    return;
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
