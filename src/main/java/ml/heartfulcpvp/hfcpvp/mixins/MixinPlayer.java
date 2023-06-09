package ml.heartfulcpvp.hfcpvp.mixins;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftPlayer.class)
public abstract class MixinPlayer {
    @Inject(
            method = "getDisplayName",
            at = @At(
                    value = "HEAD"
            )
    )
    public void onGetDisplayName(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("hoge");
    }

}
