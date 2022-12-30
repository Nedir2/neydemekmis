package me.cumhax.aehack.mod.command.commands;

import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.command.Command;

public class ReloadCommand
        extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        ae.unload(true);


    }
}

