package ml.heartfulcpvp.hfcpvp.modules.fullbright;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoinEventListener implements Listener {
    private FullBrightModule mod;

    public PlayerJoinEventListener(FullBrightModule mod) {
        this.mod = mod;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (mod.getPlayerData().isEnabled(e.getPlayer()))
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
    }
}
