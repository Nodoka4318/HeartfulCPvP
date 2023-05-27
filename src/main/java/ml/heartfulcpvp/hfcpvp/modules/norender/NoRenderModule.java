package ml.heartfulcpvp.hfcpvp.modules.norender;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.modules.autocrystal.PlayerInteractEventListener;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class NoRenderModule extends Module {
    public NoRenderModule() {
        super("NoRender", "norender", new PlayerData("norender"));

        registerPacketListener(new ParticleAnimationPacketListener(this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            toggle(player);
            return true;
        }
        sender.sendMessage("This command is only allowed for players!");
        return false;
    }
}
