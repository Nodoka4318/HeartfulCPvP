package ml.heartfulcpvp.hfcpvp.modules.autocrystal;

import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class AutoCrystalModule extends Module {
    public AutoCrystalModule() {
        super("AutoCrystal", "autocrystal", new PlayerData("autocrystal"));

        registerListener(new PlayerInteractEventListener(this));
    }

    @Override
    public void toggle(Player player) {
        if (playerData.isEnabled(player)) {
            playerData.setEnabled(player, false);
            Plugin.sendInfoMessage(player, "Disabled AutoCrystal.");
        } else {
            playerData.setEnabled(player, true);
            Plugin.sendInfoMessage(player, "Enabled AutoCrystal.");
        }
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
}
