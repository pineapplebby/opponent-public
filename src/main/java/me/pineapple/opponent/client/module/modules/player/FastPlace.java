package me.pineapple.opponent.client.module.modules.player;

import me.pineapple.opponent.api.mixin.accessors.IMinecraft;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "FastPlace", category = Module.Category.PLAYER)
public class FastPlace extends Module {

    private final Value<Boolean> noBlock = new Value<>("No Block", true);

    private final IMinecraft minecraft = ((IMinecraft) mc); // caceche

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if (mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock && noBlock.getValue())
            return;
        minecraft.setDelay(0);
    }

}
