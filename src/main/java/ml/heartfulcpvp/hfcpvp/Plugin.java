package ml.heartfulcpvp.hfcpvp;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import ml.heartfulcpvp.hfcpvp.functions.FunctionManager;
import ml.heartfulcpvp.hfcpvp.functions.playerfixer.PlayerFixer;
import ml.heartfulcpvp.hfcpvp.modules.ModuleManager;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerDataUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class Plugin extends JavaPlugin {
    public static final String pluginName = "HeartfulCPvP";
    private static ProtocolManager protocolManager;
    private static ModuleManager moduleManager;
    private static FunctionManager functionManager;
    private static SimpleCommandMap commandMap;

    @Override
    public void onEnable() {
        LoggerHolder.setLogger(getLogger());
        protocolManager = ProtocolLibrary.getProtocolManager();
        setupSimpleCommandMap();
        PlayerDataUtils.createDirectory();

        moduleManager = new ModuleManager();
        functionManager = new FunctionManager();
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

    private void setupSimpleCommandMap() {
        var spm = (SimplePluginManager) this.getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            commandMap = (SimpleCommandMap) f.get(spm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimpleCommandMap getCommandMap() {
        return commandMap;
    }
}
