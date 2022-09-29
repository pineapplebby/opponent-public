package me.pineapple.opponent.api.mixin.mixins;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.client.module.modules.other.HUD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={GuiNewChat.class})
public class MixinGuiNewChat {
    @Final
    @Shadow
    private Minecraft mc;

    @Redirect(method={"drawChat"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    public void drawChatHook1(int left, int top, int right, int bottom, int color) {
        if (!HUD.getInstance().chatTweaks.getValue().booleanValue() || !HUD.getInstance().noRect.getValue().booleanValue()) {
            Gui.drawRect((int)left, (int)top, (int)right, (int)bottom, (int)color);
        }
    }

    @Redirect(method={"drawChat"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    public int drawChatHook2(FontRenderer fontRenderer, String text, float x, float y, int color) {
        if (HUD.getInstance().chatTweaks.getValue().booleanValue() && HUD.getInstance().customFont.getValue().booleanValue()) {
            return Opponent.INSTANCE.getFontManager().drawString(text, x, y, color);
        }
        return this.mc.fontRenderer.drawStringWithShadow(text, x, y, color);
    }
}