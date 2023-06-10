package ml.heartfulcpvp.hfcpvp.modules;

import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.exceptions.HfcpvpModuleClassNotRegisteredException;
import ml.heartfulcpvp.hfcpvp.modules.autocrystal.AutoCrystalModule;
import ml.heartfulcpvp.hfcpvp.modules.fullbright.FullBrightModule;
import ml.heartfulcpvp.hfcpvp.modules.multitask.MultitaskModule;
import ml.heartfulcpvp.hfcpvp.modules.noknockback.NoKnockbackModule;
import ml.heartfulcpvp.hfcpvp.modules.noslow.NoSlowModule;
import ml.heartfulcpvp.hfcpvp.modules.surround.SurroundModule;

import java.io.IOException;
import java.util.ArrayList;

public class ModuleManager {
    private ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();

        modules.add(new AutoCrystalModule());
        modules.add(new NoKnockbackModule());
        modules.add(new NoSlowModule());
        modules.add(new FullBrightModule());
        modules.add(new SurroundModule());
        modules.add(new MultitaskModule());
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

    public Module getModule(Class moduleClass) throws HfcpvpModuleClassNotRegisteredException {
        if (!Module.class.isAssignableFrom(moduleClass)) {
            throw new HfcpvpModuleClassNotRegisteredException(moduleClass);
        } else {
            for (var m : modules) {
                if (m.getClass() == moduleClass) {
                    return m;
                }
            }

            throw new HfcpvpModuleClassNotRegisteredException(moduleClass);
        }
    }
}
