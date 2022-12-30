package me.cumhax.aehack.mod.command.commands;

import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.command.Command;

public class UnloadCommand extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        ae.unload(true);
    }
}

