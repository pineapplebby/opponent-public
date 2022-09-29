package me.pineapple.opponent.client.module.modules.player;

import me.pineapple.opponent.api.mixin.mixins.AccessorCPacketPlayer;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.PacketEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "NoFall", category = Module.Category.PLAYER)
public class NoFall extends Module {

    private final Value<Integer> distance = new Value<>("Distance", 0,0,255);

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayer) {
            final AccessorCPacketPlayer packet = (AccessorCPacketPlayer) event.getPacket();
            if (mc.player.fallDistance > distance.getValue()) {
                packet.setOnGround(true);
            }
        }
    }

}

