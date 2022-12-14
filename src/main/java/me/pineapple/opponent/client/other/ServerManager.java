package me.pineapple.opponent.client.other;

import me.pineapple.opponent.api.utils.StopWatch;
import me.pineapple.opponent.client.events.PacketEvent;
import me.pineapple.opponent.client.module.modules.other.HUD;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class ServerManager {

    private static final ServerManager INSTANCE = new ServerManager();

    private final StopWatch timer = new StopWatch();

    private float[] tpsCounts = new float[10];
    private float ticksPerSecond = 20.0f;
    private long lastUpdate = -1;

    public static ServerManager getInstance() {
        return INSTANCE;
    }

    public void load() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        reset();
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        timer.reset();
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            long currentTime = System.currentTimeMillis();

            if (lastUpdate == -1) {
                lastUpdate = currentTime;
                return;
            }

            long timeDiff = currentTime - lastUpdate;

            float tickTime = timeDiff / 20.0f;
            if (tickTime == 0) {
                tickTime = 50;
            }

            float tps = 1000 / tickTime;

            System.arraycopy(tpsCounts, 0, tpsCounts, 1, tpsCounts.length - 1);
            tpsCounts[0] = tps;

            this.ticksPerSecond = tps;
            lastUpdate = currentTime;
        }
    }

    public StopWatch getTimer() {
        return timer;
    }

    public float getTPS() {
        return ticksPerSecond;
    }

    public boolean isServerNotResponding() {
        return this.timer.hasReached(HUD.INSTANCE.serverNotResponding.getValue().intValue());
    }

    public float getAverageTPS()
    {
        float i = 0L;

        for (float j : tpsCounts)
        {
            i += j;
        }

        return i / tpsCounts.length;
    }

    public void reset() {
        this.ticksPerSecond = 20.0f;
    }

}