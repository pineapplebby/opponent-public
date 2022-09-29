package me.pineapple.opponent.client.command.commands;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.client.command.Command;
import me.pineapple.opponent.client.command.CommandManifest;

@CommandManifest(label = "Friend", aliases = {"f"})
public class FriendCommand extends Command {

    @Override
    public void execute(String[] arguments) {
        if (arguments.length < 3)
            return;

        switch (arguments[1].toUpperCase()) {
            case "ADD":
                Opponent.INSTANCE.getFriendManager().addFriend(arguments[2]);
                break;
            case "DELETE":
            case "DEL":
                Opponent.INSTANCE.getFriendManager().removeFriend(arguments[2]);
                break;
        }
    }
}
