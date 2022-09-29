package me.pineapple.opponent.api.mixin.mixins;

import me.pineapple.opponent.client.module.modules.visual.GlintModifier;
import me.pineapple.opponent.client.other.ColorHandler;
import net.minecraft.client.renderer.RenderItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(RenderItem.class)
public class MixinRenderItem {

    @ModifyArg(method = "renderEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"))
    public int getEffectColor(int color) {
        if (GlintModifier.INSTANCE.isEnabled()) {
            return ColorHandler.getColorInt();
        }
        return color;
    }

}
