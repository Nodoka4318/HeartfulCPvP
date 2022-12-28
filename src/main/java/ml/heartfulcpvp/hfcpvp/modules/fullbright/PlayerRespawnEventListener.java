package ml.heartfulcpvp.hfcpvp.modules.fullbright;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerRespawnEventListener implements Listener {
    private FullBrightModule mod;

    public PlayerRespawnEventListener(FullBrightModule mod) {
        this.mod = mod;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (mod.getPlayerData().isEnabled(e.getPlayer()))
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
    }
}
