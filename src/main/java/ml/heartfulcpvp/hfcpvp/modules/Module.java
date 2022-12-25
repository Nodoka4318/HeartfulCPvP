package ml.heartfulcpvp.hfcpvp.modules;

import com.comphenix.protocol.events.PacketListener;
import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerDataElement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class Module {
    private String modName;

    public Module(String modName) {
        this.modName = modName;
    }

    public abstract void toggle(Player player);

    public void registerListener(Listener listener) {
        LoggerHolder.getLogger().info("Registering listener, " +  listener.getClass().getName() + " for " + modName);
        Bukkit.getServer().getPluginManager().registerEvents(
                listener,
                Plugin.getPlugin()
        );
    }

    public void registerPacketListener(PacketListener listener) {
        LoggerHolder.getLogger().info("Registering packet listener, " +  listener.getClass().getName() + " for " + modName);
        Plugin.getProtocolManager().addPacketListener(listener);
    }
}
