package me.pineapple.opponent.client.module.modules.movement;

import me.pineapple.opponent.api.mixin.mixins.AccessorEntity;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.api.utils.MovementUtil;
import me.pineapple.opponent.client.events.MoveEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "NoWeb", category = Module.Category.MOVEMENT)
public class NoWeb extends Module {

    private final Value<Float> speed = new Value<>("Speed", 1.0F, 1.0F, 10.0F);

    //Dis kinda like VanillaSpeed But in Webs Haha
    @SubscribeEvent
    public void onMove(MoveEvent event) {
        if (((AccessorEntity) mc.player).getIsInWeb()) {
            final double[] calc = MovementUtil.directionSpeed(((double) speed.getValue()) / 10);
            event.setMotionX(calc[0]);
            event.setMotionZ(calc[1]);
            if (mc.gameSettings.keyBindSneak.isKeyDown()) {
                event.setMotionY(event.getMotionY() - (speed.getValue() / 10));
            }
        }
    }

}
