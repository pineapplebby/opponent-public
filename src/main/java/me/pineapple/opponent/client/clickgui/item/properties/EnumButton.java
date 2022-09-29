package me.pineapple.opponent.client.clickgui.item.properties;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.api.utils.RenderUtil;
import me.pineapple.opponent.client.clickgui.item.Button;

/**
 * @author TehNeon
 * @author nuf
 * @since May 30, 2016
 */
public class EnumButton extends Button {
    public EnumButton(String label) {
        super(label);
    }
    private Value value;

    public EnumButton(Value value) {
        super(value.getLabel());
        this.value = value;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(x, y, x + width + 7.4F, y + height, getState() ? getColor(!isHovering(mouseX, mouseY)) : !isHovering(mouseX, mouseY) ? 0x11333333 : 0x88333333);
        Opponent.INSTANCE.getFontManager().drawString(String.format("%s\2477 %s", getLabel(), value.getValue()), x + 2.3F, y - 1F + 6, getState() ? 0xFFFFFFFF : 0xFFAAAAAA);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isHovering(mouseX, mouseY)) {
            //Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1F));
            if (mouseButton == 0) {
                value.increaseEnum();
            } else if (mouseButton == 1) {
                value.decreaseEnum();
            }
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    public void toggle() {
    }

    public boolean getState() {
        return true;
    }
}
