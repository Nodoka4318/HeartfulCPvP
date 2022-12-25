package ml.heartfulcpvp.hfcpvp.modules.autocrystal;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import org.bukkit.entity.Player;

public class AutoCrystalModule extends Module {
    public AutoCrystalModule() {
        super("AutoCrystal");

        registerListener(new PlayerInteractEventListener());
    }

    @Override
    public void toggle(Player player) {

    }
}
