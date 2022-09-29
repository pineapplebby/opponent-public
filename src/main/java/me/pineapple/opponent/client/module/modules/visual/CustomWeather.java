package me.pineapple.opponent.client.module.modules.visual;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;

@ModuleManifest(label = "CustomWeather", category = Module.Category.VISUAL, listenable = false)
public class CustomWeather extends Module {

    public final Value<Boolean> snow = new Value<>("Snow", true);

    public final Value<Boolean> rainbow = new Value<>("Rainbow", false);
    public final Value<Integer> red = new Value<>("Red", 255, 0, 255);
    public final Value<Integer> green = new Value<>("Green", 255, 0, 255);
    public final Value<Integer> blue = new Value<>("Blue", 255, 0, 255);

    public static CustomWeather INSTANCE;

    {
        INSTANCE = this;
    }

}


