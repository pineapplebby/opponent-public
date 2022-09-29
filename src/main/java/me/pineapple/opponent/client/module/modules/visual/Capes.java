package me.pineapple.opponent.client.module.modules.visual;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;

@ModuleManifest(label = "Capes", category = Module.Category.VISUAL, enabled = true)
public class Capes extends Module {

    public static final Capes INSTANCE = new Capes();

    public final Value<Boolean> capes = new Value<>("Capes", true);

    }
