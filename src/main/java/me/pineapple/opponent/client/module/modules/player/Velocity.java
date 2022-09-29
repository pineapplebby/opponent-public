package me.pineapple.opponent.client.module.modules.player;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.BlockPushEvent;
import me.pineapple.opponent.client.events.CollisionApplyEvent;
import me.pineapple.opponent.client.events.PacketEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//sticks of cheese in my ass
@ModuleManifest(label = "Velocity", category = Module.Category.PLAYER)
public class Velocity extends Module {

    private final Value<Boolean> fishingHooks = new Value<>("FishingHooks", true);
    private final Value<Boolean> collision = new Value<>("Collision", true);
    private final Value<Boolean> noPush = new Value<>("NoPush", true);

    @SubscribeEvent
    public void onPacket(PacketEvent event) {
        if (isNull()) {
            return;
        }
        if (event.getPacket() instanceof SPacketEntityStatus) {
            if (fishingHooks.getValue()) {
                SPacketEntityStatus packetEntityStatus = (SPacketEntityStatus) event.getPacket();
                if (handleStatusPacket(packetEntityStatus)) {
                    event.setCanceled(true);
                }
            }
        }
        if (event.getPacket() instanceof SPacketEntityVelocity) {
            SPacketEntityVelocity packetEntityVelocity = (SPacketEntityVelocity) event.getPacket();
            if (packetEntityVelocity.getEntityID() == mc.player.getEntityId()) {
                event.setCanceled(true);
            }
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void onCollision(CollisionApplyEvent event) {
        if(collision.getValue()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onBlockPush(BlockPushEvent event) {
        if(noPush.getValue()) {
            event.setCanceled(true);
        }
    }


    private boolean handleStatusPacket(SPacketEntityStatus packetEntityStatus) {
        if(packetEntityStatus.getOpCode() == 31) {
            Entity entity = packetEntityStatus.getEntity(mc.world);
            if (entity instanceof EntityFishHook) {
                EntityFishHook entityFishHook = (EntityFishHook) entity;
                return entityFishHook.caughtEntity == mc.player;
            }
        }
        return false;
    }

}


//author zrichardnixon 4 trollgod rewrite 1/05/2021 19:55