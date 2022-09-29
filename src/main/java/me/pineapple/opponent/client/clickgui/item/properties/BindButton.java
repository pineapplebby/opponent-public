package me.pineapple.opponent.client.clickgui.item.properties;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.property.Bind;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.api.utils.RenderUtil;
import me.pineapple.opponent.client.clickgui.item.Button;
import org.lwjgl.input.Keyboard;

public class BindButton extends Button {

    private final Value value;
    public boolean isListening;

    public BindButton(Value value) {
        super(value.getLabel());
        this.value = value;
        width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(x, y, x + width + 7F, y + height - 0.5f,  0x11555555);
        if (isListening) {
            Opponent.INSTANCE.getFontManager().drawString("Binding...", x + 2, y - 1 + 6, getState() ? 0xFFFFFFFF : 0xFFAAAAAA);
        } else {
            Opponent.INSTANCE.getFontManager().drawString(value.getLabel() + " " + ChatFormatting.GRAY + value.getValue().toString(), x + 2.3F, y - 1.7F + 6, getState() ? 0xFFFFFFFF : 0xFFAAAAAA);
        }
    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {
        if (isListening) {
            Bind bind = new Bind(keyCode);
            if(bind.toString().equalsIgnoreCase("Escape")) {
                return;
            } else if(bind.getKey() == Keyboard.KEY_DELETE) {
                bind = new Bind(-1);
            }
            value.setValue(bind);
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    public void toggle() {
        isListening = !isListening;
    }

    public boolean getState() {
        return !isListening;
    }
}
