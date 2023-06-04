package ml.heartfulcpvp.hfcpvp.modules.surround;

import ml.heartfulcpvp.hfcpvp.math.Vec3d;
import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.modules.autocrystal.AutoCrystalModule;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

public class SurroundModule extends Module {
    public SurroundModule() {
        super("Surround", "surround", new PlayerData("surround"));

        registerListener(new PlayerToggleSneakEventListener(this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            toggle(player);
            return true;
        }
        sender.sendMessage("This command is only allowed for players!");
        return false;
    }

    public static void placeObsidian(Player player, Vec3d pos) {
        // ref. https://wiki.vg/Protocol#Use_Item_On
        /*
        var packet = new PacketContainer(PacketType.Play.Client.USE_ITEM);

        packet.getBlockPositionModifier().write(0, pos);
        try {
            Plugin.getProtocolManager().recieveClientPacket(player, packet);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        */

        var itemStack = player.getInventory().getItemInMainHand();
        var offStack = player.getInventory().getItemInOffHand();
        var isOffhand = false;

        if (itemStack.getType() != Material.OBSIDIAN) {
            if (offStack.getType() != Material.OBSIDIAN) {
                return;
            } else {
                isOffhand = true;
            }
        }

        List<Entity> entities = player.getNearbyEntities(2, 2, 2);

        for (Entity entity : entities) {
            if (entity.getType() == EntityType.ENDER_CRYSTAL || entity.getType() == EntityType.PLAYER) {
                var x = Math.floor(entity.getLocation().getX()) + 0.5;
                var y = Math.floor(entity.getLocation().getY());
                var z = Math.floor(entity.getLocation().getZ()) + 0.5;

                var pVec = new Vec3d(x, y, z);

                if (pVec.equals(pos)) {
                    return; // そこにプレイヤーまたはクリスタルがある
                }
            }
        }

        var block = player.getWorld().getBlockAt(pos.toLocation(player.getWorld()));
        block.setType(Material.OBSIDIAN);

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (!isOffhand) {
                // mainhand
                player.getInventory().getItemInMainHand().setAmount(itemStack.getAmount() - 1);
            } else {
                // offhand
                player.getInventory().getItemInOffHand().setAmount(offStack.getAmount() - 1);
            }
        }

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.getWorld() == player.getWorld()) {
                p.playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE, 1.0f, 1.0f); // 先にならすと違和感ある？
            }
        }
        // LoggerHolder.getLogger().warning("placed at " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
    }
}
