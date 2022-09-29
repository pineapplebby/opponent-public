package me.pineapple.opponent.client.module.modules.movement;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;

@ModuleManifest(label = "Step", category = Module.Category.MOVEMENT)
public class Step extends Module {

    private final Value<Float> height = new Value<>("Height", 2.0F,0.0F,2.5F);

    private float oldStepHeight = -1f;

    @Override
    public void onEnable() {
        if (mc.player != null) {
            oldStepHeight = mc.player.stepHeight;
            mc.player.stepHeight = height.getValue();
        }
    }

    @Override
    public void onDisable() {
        mc.player.stepHeight = oldStepHeight;
        oldStepHeight = -1f;
    }
}
