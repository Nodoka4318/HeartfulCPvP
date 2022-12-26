package ml.heartfulcpvp.hfcpvp;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import ml.heartfulcpvp.hfcpvp.modules.ModuleManager;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerDataUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Plugin extends JavaPlugin {
    public static final String pluginName = "HeartfulCPvP";
    private static ProtocolManager protocolManager;
    private static ModuleManager moduleManager;

    @Override
    public void onEnable() {
        LoggerHolder.setLogger(getLogger());
        protocolManager = ProtocolLibrary.getProtocolManager();
        PlayerDataUtils.createDirectory();

        moduleManager = new ModuleManager();
    }

    @Override
    public void onDisable() {
        moduleManager.writePlayerData();
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public static org.bukkit.plugin.Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin(Plugin.pluginName);
    }

    public static void sendInfoMessage(Player player, String message) {
        player.sendMessage("§c§l[§f§lHeartfulCPvP§c§l] §f§l" + message);
    }
}
