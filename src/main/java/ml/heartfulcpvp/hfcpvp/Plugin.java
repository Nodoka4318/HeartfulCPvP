package ml.heartfulcpvp.hfcpvp;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Plugin extends JavaPlugin {
    public static final String pluginName = "HeartfulCPvP";
    private static ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        LoggerHolder.setLogger(getLogger());
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void onDisable() {

    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public static org.bukkit.plugin.Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin(Plugin.pluginName);
    }
}
