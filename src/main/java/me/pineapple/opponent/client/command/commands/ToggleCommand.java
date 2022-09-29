package me.pineapple.opponent.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.utils.ChatUtil;
import me.pineapple.opponent.client.command.Command;
import me.pineapple.opponent.client.command.CommandManifest;

@CommandManifest(label = "Toggle", aliases = {"t", "tog"})
public class ToggleCommand extends Command {

    @Override
    public void execute(String[] arguments) {
        try {
            Opponent.INSTANCE.getModuleManager().findByLabel(arguments[1]).toggle();
            ChatUtil.printMessage(ChatFormatting.GREEN + "Toggled " + arguments[1] + "!");
        } catch (NullPointerException exception) {
            ChatUtil.printMessage(ChatFormatting.RED + "Couldnt find a module labeled " + arguments[1]);
        }
    }

}
