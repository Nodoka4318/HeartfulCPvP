package ml.heartfulcpvp.hfcpvp.modules.noslow;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import ml.heartfulcpvp.hfcpvp.Plugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.InvocationTargetException;

public class PlayerInteractEventListener implements Listener {
    private NoSlowModule mod;

    public PlayerInteractEventListener(NoSlowModule mod) {
        this.mod = mod;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!mod.getPlayerData().isEnabled(e.getPlayer()))
            return;

        var player = e.getPlayer();
        var item = e.getItem();
        if (item != null && (item.getType().isEdible() || item.getType() == Material.BOW)) {
            // TODO:
        }
    }
}
