package me.pineapple.opponent.client.module.modules.misc;

import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;

@ModuleManifest(label = "AntiPush", category = Module.Category.MISC)
public class AntiPush extends Module {

    public static AntiPush INSTANCE;

    {
        INSTANCE = this;
    }

}
