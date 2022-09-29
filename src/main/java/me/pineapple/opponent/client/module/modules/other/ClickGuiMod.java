package me.pineapple.opponent.client.module.modules.other;

import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.clickgui.ClickGui;
import me.pineapple.opponent.client.events.UpdateEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

@ModuleManifest(label = "ClickGUI", category = Module.Category.OTHER, key = Keyboard.KEY_INSERT)
public class ClickGuiMod extends Module {

    public final Value<Integer> alpha = new Value<>("Alpha", 100, 0, 255);

    private ClickGui clickGui;

    public static ClickGuiMod INSTANCE;

    {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        if (clickGui == null) {
            clickGui = new ClickGui();
        }
        mc.displayGuiScreen(clickGui);
    }

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if (mc.currentScreen != clickGui) {
            setEnabled(false);
        }
    }

}
