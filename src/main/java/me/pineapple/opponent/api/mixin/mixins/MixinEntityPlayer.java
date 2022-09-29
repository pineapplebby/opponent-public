package me.pineapple.opponent.api.mixin.mixins;

import me.pineapple.opponent.client.events.JumpEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

    Minecraft mc = Minecraft.getMinecraft();

    @Inject(method = "jump", at = @At("HEAD"))
    public void jump(CallbackInfo ci) {
        if ((Object) this == mc.player) {
            MinecraftForge.EVENT_BUS.post(new JumpEvent());
        }
    }

}
