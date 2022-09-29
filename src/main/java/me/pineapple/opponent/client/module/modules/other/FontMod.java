package me.pineapple.opponent.client.module.modules.other;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.events.ClientEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

@ModuleManifest(label = "Font", category = Module.Category.OTHER)
public class FontMod extends Module {

    public static FontMod INSTANCE;

    {
        INSTANCE = this;
    }

    public final Value<Style> STYLE = new Value<>("Style", Style.PLAIN);

    public static void init() {
        Opponent.INSTANCE.getFontManager().updateFont();
    }

    @SubscribeEvent
    public void onSetting(ClientEvent event) {
        if (event.getProperty() == STYLE) {
            System.out.println("font");
            Opponent.INSTANCE.getFontManager().updateFont();
        }
    }

    @Override
    public void onDisable() {
        Opponent.INSTANCE.getFontManager().setCustomFont(false);
    }

    @Override
    public void onEnable() {
        Opponent.INSTANCE.getFontManager().setCustomFont(true);
    }

    public enum Style {
        PLAIN(Font.PLAIN),
        BOLD(Font.BOLD),
        ITALIC(Font.ITALIC);

        private final int type;

        Style(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

}
