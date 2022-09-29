package me.pineapple.opponent.client.command.commands;

import me.pineapple.opponent.api.utils.ChatUtil;
import me.pineapple.opponent.client.command.Command;
import me.pineapple.opponent.client.command.CommandManifest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.tutorial.TutorialSteps;

@CommandManifest(label = "Tutorial", aliases = {"t", "tut"})
public class TutorialCommand extends Command {

    @Override
    public void execute(String[] arguments) {
        ChatUtil.printMessageWithID("Set tutorial step to none", -222114);
        Minecraft.getMinecraft().gameSettings.tutorialStep = TutorialSteps.NONE;
        Minecraft.getMinecraft().getTutorial().setStep(TutorialSteps.NONE);
    }
}
