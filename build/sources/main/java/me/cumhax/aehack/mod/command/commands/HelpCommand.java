package me.cumhax.aehack.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.command.Command;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : ae.commandManager.getCommands()) HelpCommand.sendMessage(ChatFormatting.GRAY + ae.commandManager.getPrefix() + command.getName());
    }
}

