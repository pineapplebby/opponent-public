package me.pineapple.opponent.client.module.modules.movement;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.MoveEvent;
import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "LiquidTweaks", category = Module.Category.MOVEMENT)
public class LiquidTweaks extends Module {

    public Value<Boolean> zooom = new Value<>("Zoooom", true);
    public Value<Double> horizontal = new Value<>("Horizontal", 4.0, 1.0, 20.0);

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if (event.getStage() == UpdateEvent.Stage.POST)
            return;
        if (mc.player.isInLava()) {
            if (mc.gameSettings.keyBindSneak.isKeyDown()) {
                mc.player.motionY -= 0.0715;
            } else if (mc.gameSettings.keyBindJump.isKeyDown()) {
                mc.player.motionY += 0.00999;
            }
        }
    }

    @SubscribeEvent
    public void onMove(MoveEvent event) {
        if (!zooom.getValue())
            return;
        if (mc.player.isInLava() && !mc.player.onGround) {
            event.setMotionX(event.getMotionX() * horizontal.getValue());
            event.setMotionZ(event.getMotionZ() * horizontal.getValue());
        }
    }

}
