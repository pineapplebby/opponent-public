package me.pineapple.opponent.api.mixin.mixins;

import me.pineapple.opponent.api.mixin.accessors.IMinecraft;
import me.pineapple.opponent.client.events.ResizeEvent;
import me.pineapple.opponent.client.module.modules.misc.MiddleClick;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements IMinecraft {

    @Accessor("rightClickDelayTimer")
    public abstract void setDelay(int delay);

    @Inject(method = "resize", at = @At("RETURN"))
    public void resize(int width, int height, CallbackInfo ci) {
        if (Minecraft.getMinecraft().player == null)
            return;
        MinecraftForge.EVENT_BUS.post(new ResizeEvent());
    }

    @Inject(method = "middleClickMouse", at = @At("HEAD"))
    public void middleClick(CallbackInfo ci) {
        if (MiddleClick.INSTANCE.isEnabled()) {
            MiddleClick.INSTANCE.onMouse();
        }
    }

}
