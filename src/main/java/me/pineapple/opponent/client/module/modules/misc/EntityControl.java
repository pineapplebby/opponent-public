package me.pineapple.opponent.client.module.modules.misc;

import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;

@ModuleManifest(label = "EntityControl", category = Module.Category.MISC)
public class EntityControl extends Module {

    public static EntityControl INSTANCE;

    {
        INSTANCE = this;
    }

}
