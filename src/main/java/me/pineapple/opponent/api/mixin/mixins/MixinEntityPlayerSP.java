package me.pineapple.opponent.api.mixin.mixins;

import com.mojang.authlib.GameProfile;
import me.pineapple.opponent.client.events.MoveEvent;
import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.modules.misc.AntiPush;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer {

    @Shadow
    protected Minecraft mc;

    public MixinEntityPlayerSP(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    @Override
    public void move(MoverType type, double x, double y, double z) {
        final MoveEvent event = new MoveEvent(x, y, z);
        MinecraftForge.EVENT_BUS.post(event);
        super.move(type, event.getMotionX(), event.getMotionY(), event.getMotionZ());
    }

    @Inject(method = "pushOutOfBlocks", at = @At("HEAD"), cancellable = true)
    public void pushOutOfBlocks(double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (AntiPush.INSTANCE.isEnabled()) {
            cir.cancel();
        }
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void onUpdateWalking(CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new UpdateEvent(UpdateEvent.Stage.PRE));
    }

    @Inject(method = "onUpdate", at = @At("RETURN"))
    public void onUpdateWalkingPost(CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new UpdateEvent(UpdateEvent.Stage.POST));
    }

}
