package me.pineapple.opponent.client.events;

import me.pineapple.opponent.api.property.Value;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ClientEvent extends Event {

    private final Value value;

    public ClientEvent(Value value) {
        this.value = value;
    }

    public Value getProperty() {
        return value;
    }
}
