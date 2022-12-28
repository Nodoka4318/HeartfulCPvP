package ml.heartfulcpvp.hfcpvp.modules.fullbright;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FullBrightModule extends Module {
    public FullBrightModule() {
        super("FullBright", "fullbright", new PlayerData("fullbright"));

        registerListener(new PlayerJoinEventListener(this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            toggle(player);

            if (playerData.isEnabled(player)) {
                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                }
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
            }
            return true;
        }
        sender.sendMessage("This command is only allowed for players!");
        return false;
    }
}
