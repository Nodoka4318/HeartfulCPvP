package ml.heartfulcpvp.hfcpvp.mixin.mixins;

import com.dragoncommissions.mixbukkit.api.action.impl.MActionInsertShellCode;
import com.dragoncommissions.mixbukkit.api.locator.impl.HLocatorHead;
import com.dragoncommissions.mixbukkit.api.shellcode.impl.api.CallbackInfo;
import com.dragoncommissions.mixbukkit.api.shellcode.impl.api.ShellCodeReflectionMixinPluginMethodCall;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.exceptions.HfcpvpException;
import ml.heartfulcpvp.hfcpvp.mixin.IMixin;
import ml.heartfulcpvp.hfcpvp.modules.multitask.MultitaskModule;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EntityPlayer;

public class MixinEntityLiving implements IMixin {
    @Override
    public void mixin() {
        var plugin = Plugin.getMixinPlugin();

        try {
            plugin.registerMixin(
                    "entityplayer_ishandraised",
                    new MActionInsertShellCode(
                            new ShellCodeReflectionMixinPluginMethodCall(MixinEntityLiving.class.getDeclaredMethod("isHandRaisedWrapper", EntityLiving.class, CallbackInfo.class)),
                            new HLocatorHead()
                    ),
                    EntityLiving.class,
                    "isHandRaised",
                    boolean.class
            );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void isHandRaisedWrapper(EntityLiving entity, CallbackInfo info) {
        if (entity instanceof EntityPlayer player) {
            var moduleManager = Plugin.getModuleManager();

            try {
                // Multitask
                var multitaskModule = moduleManager.getModule(MultitaskModule.class);
                if (multitaskModule.getPlayerData().isEnabled(player.getBukkitEntity())) {
                    info.setReturned(true);
                    info.setReturnValue(false);
                }
            } catch (HfcpvpException ex) {
                ex.printStackTrace();
            }
        }
    }
}
