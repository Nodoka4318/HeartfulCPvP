package ml.heartfulcpvp.hfcpvp.functions.crystalfixer;

import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.functions.Function;
import ml.heartfulcpvp.hfcpvp.modules.autocrystal.AutoCrystalModule;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Objects;

public class CrystalFixer extends Function {
    public CrystalFixer() {
        registerListener(new Listener() {
            @EventHandler
            public void fixInventory(PlayerInteractEvent e) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    var player = e.getPlayer();

                    int amountMainhand = -1;
                    int amountOffhand = -1;

                    try {
                        amountMainhand = player.getItemInHand().getAmount();
                        amountOffhand = player.getInventory().getItemInOffHand().getAmount();
                        // debug msg
                        e.getPlayer().sendMessage(amountMainhand + " " + amountOffhand);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }

                    var block = Objects.requireNonNull(e.getClickedBlock()).getType();
                    if (!(block == Material.OBSIDIAN || block == Material.BEDROCK))
                        return;

                    if (!(player.getItemInHand().getType() == Material.END_CRYSTAL
                            || player.getInventory().getItemInOffHand().getType() == Material.END_CRYSTAL))
                        return;

                    var top = e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation().add(0, 2, 0)).getType();
                    if (top != Material.AIR) {
                        if (player.getItemInHand().getType() == Material.END_CRYSTAL) {
                            if (amountMainhand != -1)
                                player.getItemInHand().setAmount(amountMainhand >= 64 ? 64 : amountMainhand + 1);
                        }
                        // TODO:
                        if (player.getInventory().getItemInOffHand().getType() == Material.END_CRYSTAL) {
                            if (amountOffhand != -1)
                                player.getInventory().getItemInOffHand().setAmount(amountOffhand);
                        }
                    }
                }
            }

            @EventHandler
            public void deleteCrystal(PlayerInteractEvent e) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    var block = Objects.requireNonNull(e.getClickedBlock()).getType();
                    if (!(block == Material.OBSIDIAN || block == Material.BEDROCK))
                        return;

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getPlugin(), () -> {
                        var entities = e.getPlayer().getNearbyEntities(4, 4, 3);

                        for (Entity entity : entities) {
                            if (entity.getType() == EntityType.ENDER_CRYSTAL) {
                                var top = entity.getLocation().getBlock().getRelative(BlockFace.UP);
                                if (top.getType() != Material.AIR) {
                                    entity.remove();
                                }
                            }
                        }
                    }, 0);
                }
            }
        });
    }
}
