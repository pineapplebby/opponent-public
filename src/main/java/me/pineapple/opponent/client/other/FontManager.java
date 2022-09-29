package me.pineapple.opponent.client.other;

import me.pineapple.opponent.api.utils.font.CFontRenderer;
import me.pineapple.opponent.client.module.modules.other.FontMod;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class FontManager {

    private final Minecraft mc = Minecraft.getMinecraft();

    private CFontRenderer fontRenderer = new CFontRenderer(new Font("Verdana", 0, 18), true, true);
    private boolean customFont;

    public boolean isCustomFont() {
        return customFont;
    }

    public void setCustomFont(boolean customFont) {
        this.customFont = customFont;
    }

    public void updateFont() {
        //noinspection MagicConstant
        fontRenderer = new CFontRenderer(new Font("Verdana", FontMod.INSTANCE.STYLE.getValue().getType(), 18), true, true);
    }

    public int drawString(String text, float x, float y, int color) {
        if (customFont) {
            fontRenderer.drawStringWithShadow(text, x, y, color);
            return color;
        }
        mc.fontRenderer.drawStringWithShadow(text, x, y, color);
        return color;
    }

    public int getStringWidth(String text) {
        if (customFont) {
            return fontRenderer.getStringWidth(text);
        }
        return mc.fontRenderer.getStringWidth(text);
    }

}
