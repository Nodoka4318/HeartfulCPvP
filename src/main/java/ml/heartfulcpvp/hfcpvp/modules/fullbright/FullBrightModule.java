package ml.heartfulcpvp.hfcpvp.modules.fullbright;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class FullBrightModule extends Module {
    public FullBrightModule() {
        super("FullBright", "fullbright", new PlayerData("fullbright"));

        registerListener(new PlayerJoinEventListener(this));
        registerListener(new PlayerMoveEventListener(this));
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.stream(new String[] { "nightvision", "nv" }).toList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            toggle(player);

            if (playerData.isEnabled(player)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
            } else {
                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                }
            }
            return true;
        }
        sender.sendMessage("This command is only allowed for players!");
        return false;
    }
}
