package ml.heartfulcpvp.hfcpvp.modules;

import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.modules.autocrystal.AutoCrystalModule;

import java.io.IOException;
import java.util.ArrayList;

public class ModuleManager {
    private ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();

        modules.add(new AutoCrystalModule());
    }

    public void writePlayerData() {
        modules.forEach((m) -> {
            try {
                m.getPlayerData().write();
                LoggerHolder.getLogger().info("<" + m.modName + "> Wrote " + m.getPlayerData().getSize() + " players' data.");
            } catch (IOException e) {
                LoggerHolder.getLogger().severe("<" + m.modName + "> An error occurred while writing player data.");
                e.printStackTrace();
            }
        });
    }
}
