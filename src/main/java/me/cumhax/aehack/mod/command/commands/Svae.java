package me.cumhax.aehack.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.command.Command;

import static me.cumhax.aehack.ae.configManager;

public class Svae extends Command {
    public Svae() {
        super("Save", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        configManager.saveConfig(ae.configManager.config.replaceFirst("claudius/", ""));
        Command.sendMessage("Prolly Saved Ur configs" + ChatFormatting.BLUE);
    }
}

