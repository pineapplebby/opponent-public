package me.pineapple.opponent.client.module.modules.misc;

import me.pineapple.opponent.api.utils.RenderUtil;
import me.pineapple.opponent.api.utils.Timer;
import me.pineapple.opponent.client.events.PacketEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

@ModuleManifest(label = "ChorusViewer", category = Module.Category.MISC)
public class ChorusViewer extends Module {

    private final Timer timer = new Timer();

    private BlockPos chorusPos;

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();
            if (packet.getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT) {
                chorusPos = new BlockPos(packet.getX(), packet.getY(), packet.getZ());
                timer.reset();
            }
        }
    }

    @Override
    public void onRender3D() {
        if (chorusPos != null) {
            if (timer.passed(2000)) {
                chorusPos = null;
                return;
            }
            RenderUtil.drawBox(chorusPos, new Color(255, 0, 255), 100);
        }
    }
}
