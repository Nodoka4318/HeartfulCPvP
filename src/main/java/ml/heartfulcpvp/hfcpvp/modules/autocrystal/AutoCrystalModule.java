package ml.heartfulcpvp.hfcpvp.modules.autocrystal;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class AutoCrystalModule extends Module {
    public AutoCrystalModule() {
        super("AutoCrystal", "autocrystal", new PlayerData("autocrystal"));

        // registerListener(new PlayerInteractEventListener(this));
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.stream(new String[] { "ac", "ca", "crystalaura" }).toList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            var player = (Player) sender;
            toggle(player);
            return true;
        }
        sender.sendMessage("This command is only allowed for players!");
        return false;
    }

    public static void breakCrystal(Player player, EnderCrystal entity) {
        var packet = new PacketContainer(PacketType.Play.Client.USE_ENTITY);

        packet.getEntityUseActions().write(0, EnumWrappers.EntityUseAction.ATTACK);
        packet.getModifier().write(0, entity.getEntityId());
        try {
            Plugin.getProtocolManager().recieveClientPacket(player, packet);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}
