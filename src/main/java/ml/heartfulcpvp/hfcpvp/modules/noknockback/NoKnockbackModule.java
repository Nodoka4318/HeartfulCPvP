package ml.heartfulcpvp.hfcpvp.modules.noknockback;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.server.TemporaryPlayer;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class NoKnockbackModule extends Module {
    public NoKnockbackModule() {
        super("NoKnock-back", "noknockback", new PlayerData("noknockback"));

        registerPacketListener(new PacketAdapter(
                Plugin.getPlugin(),
                ListenerPriority.NORMAL,
                PacketType.Play.Server.ENTITY_VELOCITY
        ) {
            @Override
            public void onPacketSending(PacketEvent e) {
                var player = e.getPlayer();
                if (player instanceof TemporaryPlayer || player == null)
                    return;

                if (playerData.isEnabled(e.getPlayer())) {
                    if (e.getPacket().getIntegers().read(0) != e.getPlayer().getEntityId())
                        return;

                    e.setCancelled(true);
                }
            }
        });

        registerPacketListener(new PacketAdapter(
                Plugin.getPlugin(),
                ListenerPriority.NORMAL,
                PacketType.Play.Server.EXPLOSION
        ) {
            @Override
            public void onPacketSending(PacketEvent e) {
                var player = e.getPlayer();
                if (player instanceof TemporaryPlayer || player == null)
                    return;

                if (playerData.isEnabled(e.getPlayer())) {
                    var packet = e.getPacket();
                    // ref: https://wiki.vg/Protocol#Explosion
                    packet.getFloat().write(1, 0f);
                    packet.getFloat().write(2, 0f);
                    packet.getFloat().write(3, 0f);

                    e.setPacket(packet);
                }
            }
        });
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

    @Override
    public List<String> getCommandAliases() {
        return Arrays.stream(new String[] { "velocity" }).toList();
    }
}
