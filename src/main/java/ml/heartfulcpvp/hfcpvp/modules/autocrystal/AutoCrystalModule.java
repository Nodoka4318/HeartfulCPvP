package ml.heartfulcpvp.hfcpvp.modules.autocrystal;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class AutoCrystalModule extends Module {
    public AutoCrystalModule() {
        super("AutoCrystal", "autocrystal", new PlayerData("autocrystal"));

        registerListener(new PlayerInteractEventListener(this));
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.stream(new String[] { "ac", "ca", "crystalaura" }).toList();
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
