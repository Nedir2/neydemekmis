package me.cumhax.aehack.mod.command.commands;

import me.cumhax.aehack.mod.command.Command;

import java.awt.*;
import java.net.URI;

public class NameMC extends Command {
    public NameMC() {
        super("namemc", new String[]{"<name>"});
    }


    @Override
    public void execute(String[] commands) {
        final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI("https://namemc.com/profile/" + commands[0]));
            } catch (Throwable ignored) {
            }
        }

    }
}
