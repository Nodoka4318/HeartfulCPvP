package ml.heartfulcpvp.hfcpvp.mixins;

import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.exceptions.HfcpvpException;
import ml.heartfulcpvp.hfcpvp.modules.multitask.MultitaskModule;
import net.minecraft.server.v1_16_R3.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityLiving.class)
public abstract class MixinEntityLiving {
    @Redirect(
            method = "c(Lnet/minecraft/server/v1_16_R3/EnumHand;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/v1_16_R3/EntityLiving;isHandRaised()Z"
            )
    )
    public boolean isHandRaisedWrapper(EntityLiving entity) {
        if (entity instanceof EntityPlayer player) {
            var moduleManager = Plugin.getModuleManager();

            try {
                // Multitask
                var multitaskModule = moduleManager.getModule(MultitaskModule.class);
                if (multitaskModule.getPlayerData().isEnabled(player.getBukkitEntity())) {
                    player.getBukkitEntity().sendMessage("Yeah");
                    return false;
                }
            } catch (HfcpvpException ex) {
                ex.printStackTrace();
                return player.isHandRaised();
            }
        } else {
            return entity.isHandRaised();
        }

        return entity.isHandRaised(); // ???
    }
}
