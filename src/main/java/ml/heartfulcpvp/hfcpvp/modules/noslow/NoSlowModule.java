package ml.heartfulcpvp.hfcpvp.modules.noslow;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoSlowModule extends Module {
    public NoSlowModule() {
        super("NoSlow", "noslow", new PlayerData("noslow"));

        registerListener(new PlayerInteractEventListener(this));
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
