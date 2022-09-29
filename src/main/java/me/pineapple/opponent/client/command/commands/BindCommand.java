package me.pineapple.opponent.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.utils.ChatUtil;
import me.pineapple.opponent.client.command.Command;
import me.pineapple.opponent.client.command.CommandManifest;
import me.pineapple.opponent.client.module.Module;
import org.lwjgl.input.Keyboard;

@CommandManifest(label = "Bind", aliases = {"b"})
public class BindCommand extends Command {

    @Override
    public void execute(String[] arguments) {
        if (arguments.length < 2) {
            ChatUtil.printMessage(ChatFormatting.RED + "Not enough arguments!");
            return;
        }
        final Module module = Opponent.INSTANCE.getModuleManager().findByLabel(arguments[1]);
        if (module == null) {
            ChatUtil.printMessage(ChatFormatting.RED + "Couldnt find a module labeled " + arguments[1] + "!");
            return;
        }

        int keyIndex = Keyboard.getKeyIndex(arguments[2].toUpperCase());
        module.setKey(keyIndex);
        ChatUtil.printMessage(ChatFormatting.GREEN + "Bound " + module.getLabel());
    }

}
