package ml.heartfulcpvp.hfcpvp.features;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import ml.heartfulcpvp.hfcpvp.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;

import static ml.heartfulcpvp.hfcpvp.LoggerHolder.getLogger;

public class PlayerFixer implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        var fakePacket = new PacketContainer(PacketType.Play.Client.ARM_ANIMATION);
        try {
            fakePacket.getIntegers().write(0, 0);
        } catch (Exception ex) {
            getLogger().warning(ex.getLocalizedMessage());
        } finally {
            try {
                Plugin.getProtocolManager().recieveClientPacket(e.getPlayer(), fakePacket);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }
}
