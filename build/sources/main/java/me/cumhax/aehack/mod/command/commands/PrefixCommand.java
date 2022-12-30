package me.cumhax.aehack.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.command.Command;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(ChatFormatting.GREEN + "Current prefix is " + ae.commandManager.getPrefix());
            return;
        }
        ae.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + ChatFormatting.GRAY + commands[0]);
    }
}

