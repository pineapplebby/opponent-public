package me.pineapple.opponent.client.module.modules.player;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.PacketEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "XCarry", category = Module.Category.PLAYER)
public class XCarry extends Module {
//Borby
    private final Value<Boolean> forceCancel = new Value<>("ForceCancel",true);

    @SubscribeEvent
    public void onUpdate(PacketEvent event) {
        if (event.getPacket() instanceof CPacketCloseWindow){
            if (forceCancel.getValue() ) {
                event.setCanceled(true);
            }
            else {
                for (int i = 1; i <= 4; i++) {
                    if (mc.player.inventory.getStackInSlot(i).getItem() != Items.AIR)
                        event.setCanceled(true);
                }
            }
        }
    }
}

