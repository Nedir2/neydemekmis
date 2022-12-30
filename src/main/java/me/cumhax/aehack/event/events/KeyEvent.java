package me.cumhax.aehack.event.events;

import me.cumhax.aehack.event.EventStage;

public class KeyEvent
        extends EventStage {
    private final int key;
    public Object info;
    public Object pressed;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}

