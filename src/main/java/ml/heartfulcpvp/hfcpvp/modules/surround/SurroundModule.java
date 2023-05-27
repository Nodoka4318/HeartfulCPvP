package ml.heartfulcpvp.hfcpvp.modules.surround;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.math.Vec3d;
import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

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

    public static void placeBlock(Player player, Vec3d pos) {
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
        if (itemStack.getType() != Material.OBSIDIAN) {
            return;
        }

        var block = player.getWorld().getBlockAt(pos.toLocation(player.getWorld()));
        block.setType(Material.OBSIDIAN);

        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getInventory().getItemInMainHand().setAmount(itemStack.getAmount() - 1);
        }

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE, 1.0f, 1.0f);
        }
        // LoggerHolder.getLogger().warning("placed at " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
    }
}
