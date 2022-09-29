package me.pineapple.opponent.api.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class ChatUtil {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final String prefix = String.valueOf(new TextComponentString("\u00a7r" + ChatFormatting.WHITE + "[\u00a7ropponent\u00a7r] "));

    public static void printMessage(String message) {
        if (mc.player == null) {
            return;
        }

        mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(prefix + message));
    }

    public static void printMessageWithID(String message, int id) {
        if (mc.player == null) {
            return;
        }

        mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString(prefix + message), id);
    }

}
