package me.pineapple.opponent.client.module.modules.misc;

import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "AutoGG", category = Module.Category.MISC)
public class AutoGG extends Module {

    private static EntityPlayer currentTarget;

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if (currentTarget != null && mc.player.getDistanceSq(currentTarget) < 100) {
            if (currentTarget.isDead || currentTarget.getHealth() <= 0) {
                mc.player.sendChatMessage("ez " + currentTarget.getName());
                currentTarget = null;
            }
        }
    }

    public static void setCurrentTarget(EntityPlayer player) {
        currentTarget = player;
    }

}
