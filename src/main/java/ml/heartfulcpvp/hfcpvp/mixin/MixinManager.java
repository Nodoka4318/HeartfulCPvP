package ml.heartfulcpvp.hfcpvp.mixin;

import ml.heartfulcpvp.hfcpvp.LoggerHolder;
import ml.heartfulcpvp.hfcpvp.mixin.mixins.MixinEntityLiving;

import java.util.ArrayList;

public class MixinManager {
    private ArrayList<IMixin> mixins;

    public MixinManager() {
        mixins = new ArrayList<>();

        mixins.add(new MixinEntityLiving());

        mixin();
    }

    private void mixin() {
        for (var m : mixins) {
            LoggerHolder.getLogger().info("Initializing mixin class, " + m.getClass().getSimpleName());
            m.mixin();
        }
    }
}
