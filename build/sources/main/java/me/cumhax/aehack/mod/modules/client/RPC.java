package me.cumhax.aehack.mod.modules.client;

import me.cumhax.aehack.DiscordPresence;
import me.cumhax.aehack.mod.modules.Module;

public class RPC
        extends Module {
    public static RPC INSTANCE;

    public RPC() {
        super("RPC", "Discord rich presence", Module.Category.CLIENT, false, false, false);
        this.enabled.setValue(true);
        this.drawn.setValue(false);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        DiscordPresence.start();
    }

    @Override
    public void onDisable() {
        DiscordPresence.stop();
    }
}

