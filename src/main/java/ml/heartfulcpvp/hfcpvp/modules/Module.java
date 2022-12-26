package ml.heartfulcpvp.hfcpvp.modules;

import com.comphenix.protocol.events.PacketListener;
import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerDataElement;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.IOException;

public abstract class Module {
    protected String modName;
    protected String commandLabel;
    protected PlayerData playerData;

    public Module(String modName, String commandLabel, PlayerData playerData) {
        this.modName = modName;
        this.commandLabel = commandLabel;
        this.playerData = playerData;

        try {
            playerData.load();
            LoggerHolder.getLogger().info("<" + modName + "> Loaded " + playerData.getSize() + " players' data.");
        } catch (IOException e) {
            LoggerHolder.getLogger().severe("<" + modName + "> An error occurred while loading player data.");
            e.printStackTrace();
        }
        Bukkit.getPluginCommand(commandLabel).setExecutor(Module.this::onCommand);
    }

    public abstract void toggle(Player player);

    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

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

    public PlayerData getPlayerData() {
        return playerData;
    }
}
