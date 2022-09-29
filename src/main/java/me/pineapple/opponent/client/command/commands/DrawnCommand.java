package me.pineapple.opponent.client.command.commands;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.utils.ChatUtil;
import me.pineapple.opponent.client.command.Command;
import me.pineapple.opponent.client.command.CommandManifest;
import me.pineapple.opponent.client.module.Module;

@CommandManifest(label = "drawn", aliases = {"hide"})
public class DrawnCommand extends Command {

    @Override
    public void execute(String[] arguments) {
        Module module = Opponent.INSTANCE.getModuleManager().findByLabel(arguments[1]);
        if (module != null) {
            module.setDrawn(!module.isDrawn());
            ChatUtil.printMessage(module.getLabel() + " has been " + (module.isDrawn() ? "unhidden" : "hidden"));
        }
    }

}
