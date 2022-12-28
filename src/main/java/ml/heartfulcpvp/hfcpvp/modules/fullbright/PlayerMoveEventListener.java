package ml.heartfulcpvp.hfcpvp.modules.fullbright;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveEventListener implements Listener {
    private FullBrightModule mod;

    public PlayerMoveEventListener(FullBrightModule mod) {
        this.mod = mod;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (mod.getPlayerData().isEnabled(e.getPlayer()))
            if (!e.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION))
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
    }
}
