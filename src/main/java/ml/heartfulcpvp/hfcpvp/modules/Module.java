package ml.heartfulcpvp.hfcpvp.modules;

import com.comphenix.protocol.events.PacketListener;
import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        // Bukkit.getPluginCommand(commandLabel).setExecutor(Module.this::onCommand);
        registerCommand();
    }

    public String getCommandDescription() {
        return "toggle " + modName;
    }

    public List<String> getCommandAliases() {
        return Arrays.stream(new String[] { }).toList();
    }

    public void toggle(Player player) {
        if (playerData.isEnabled(player)) {
            playerData.setEnabled(player, false);
            Plugin.sendInfoMessage(player, "Disabled " + modName + ".");
        } else {
            playerData.setEnabled(player, true);
            Plugin.sendInfoMessage(player, "Enabled " + modName + ".");
        }
    }

    // こいつも基底クラスで実装しちゃってもいいかも
    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    protected void registerListener(Listener listener) {
        LoggerHolder.getLogger().info("Registering listener, " +  listener.getClass().getName() + " for " + modName);
        Bukkit.getServer().getPluginManager().registerEvents(
                listener,
                Plugin.getPlugin()
        );
    }

    protected void registerPacketListener(PacketListener listener) {
        LoggerHolder.getLogger().info("Registering packet listener, " +  listener.getClass().getName() + " for " + modName);
        Plugin.getProtocolManager().addPacketListener(listener);
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    protected void registerCommand() {
        Command command = new Command(commandLabel) {
            @Override
            public boolean execute(CommandSender sender, String label, String[] args) {
                return Module.this.onCommand(sender, this, label, args);
            }
        };
        command.setDescription(getCommandDescription());
        if (getCommandAliases().size() >= 1) {
            command.setAliases(getCommandAliases());
        }
        LoggerHolder.getLogger().info("Registering command, " +  commandLabel + " for " + modName);
        Plugin.getCommandMap().register(commandLabel, command);
    }
}
