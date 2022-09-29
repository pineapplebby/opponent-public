package me.pineapple.opponent.client.module.modules.misc;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.api.utils.ItemUtil;
import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.init.Items;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "AutoLog", category = Module.Category.MISC)
public class AutoLog extends Module {

    private final Value<Boolean> onlyNoTotem = new Value<>("No Totem Only", true);

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if (Opponent.INSTANCE.getSafetyManager().isSafe()) {
            if (ItemUtil.getItemCount(Items.TOTEM_OF_UNDYING) == 0 || !onlyNoTotem.getValue()) {
                mc.getConnection().handleDisconnect(new SPacketDisconnect());
            }
        }
    }

}
