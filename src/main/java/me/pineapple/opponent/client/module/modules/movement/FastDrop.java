package me.pineapple.opponent.client.module.modules.movement;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "FastDrop", category = Module.Category.MOVEMENT)
public class FastDrop extends Module {

    private final Value<Float> speed = new Value<>("Speed", 0.0F, 0.0F, 20.0F);

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if (isNull())
            return;
        if (mc.player.isInWater() || mc.player.isOnLadder() || mc.player.isInLava()) {
            return;
        }
        if (mc.player.onGround) {
            mc.player.motionY -= speed.getValue() / 10;
        }
    }
}
