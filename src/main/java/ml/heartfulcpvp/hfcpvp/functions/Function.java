package ml.heartfulcpvp.hfcpvp.functions;

import com.comphenix.protocol.events.PacketListener;
import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.modules.Module;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Function {
    protected void registerListener(Listener listener) {
        LoggerHolder.getLogger().info("Registering listener, " +  listener.getClass().getName() + " for " + this.getClass().getSimpleName());
        Bukkit.getServer().getPluginManager().registerEvents(
                listener,
                Plugin.getPlugin()
        );
    }

    protected void registerPacketListener(PacketListener listener) {
        LoggerHolder.getLogger().info("Registering packet listener, " +  listener.getClass().getName() + " for " + this.getClass().getSimpleName());
        Plugin.getProtocolManager().addPacketListener(listener);
    }

    protected void registerCommand(Command command) {
        LoggerHolder.getLogger().info("Registering command, " +  command.getName() + " for " + this.getClass().getSimpleName());
        Plugin.getCommandMap().register(command.getName(), command);
    }
}
