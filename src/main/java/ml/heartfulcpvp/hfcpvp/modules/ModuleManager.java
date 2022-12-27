package ml.heartfulcpvp.hfcpvp.modules;

import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.modules.autocrystal.AutoCrystalModule;
import ml.heartfulcpvp.hfcpvp.modules.noknockback.NoKnockbackModule;
import ml.heartfulcpvp.hfcpvp.modules.noslow.NoSlowModule;

import java.io.IOException;
import java.util.ArrayList;

public class ModuleManager {
    private ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();

        modules.add(new AutoCrystalModule());
        modules.add(new NoKnockbackModule());
        modules.add(new NoSlowModule());
    }

    public void writePlayerData() {
        modules.forEach((m) -> {
            try {
                m.getPlayerData().write();
                LoggerHolder.getLogger().info("<" + m.modName + "> Saved " + m.getPlayerData().getSize() + " players' data.");
            } catch (IOException e) {
                LoggerHolder.getLogger().severe("<" + m.modName + "> An error occurred while saving player data.");
                e.printStackTrace();
            }
        });
    }
}
