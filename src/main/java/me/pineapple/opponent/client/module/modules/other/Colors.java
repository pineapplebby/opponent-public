package me.pineapple.opponent.client.module.modules.other;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.ClientEvent;
import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import me.pineapple.opponent.client.other.ColorHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleManifest(label = "Colors", persistent = true, category = Module.Category.OTHER)
public class Colors extends Module {

    public static final Value<Integer> speed = new Value<>("Speed", 100, 1, 150);
    public static final Value<Integer> saturation = new Value<>("Saturation", 255, 0, 255);
    public static final Value<Integer> brightness = new Value<>("Brightness", 255, 0, 255);

    public static final Value<Integer> red = new Value<>("Red", 255, 0, 255);
    public static final Value<Integer> green = new Value<>("Green", 255, 0, 255);
    public static final Value<Integer> blue = new Value<>("Blue", 255, 0, 255);

    int ticks;

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if (ticks++ < 10) {
            ColorHandler.setColor(red.getValue(), green.getValue(), blue.getValue());
        }
    }

    @SubscribeEvent
    public void onClientEvent(ClientEvent event) {
        if (event.getProperty() == red || event.getProperty() == green || event.getProperty() == blue) {
            ColorHandler.setColor(red.getValue(), green.getValue(), blue.getValue());
        }
    }

}
