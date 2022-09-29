package me.pineapple.opponent.client.module.modules.visual;

import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;

@ModuleManifest(label = "GlintModifier", category = Module.Category.VISUAL, listenable = false)
public class GlintModifier extends Module {

    public static GlintModifier INSTANCE;

    {
        INSTANCE = this;
    }

}
