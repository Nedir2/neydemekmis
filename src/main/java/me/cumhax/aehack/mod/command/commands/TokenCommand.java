package me.cumhax.aehack.mod.command.commands;



import me.cumhax.aehack.mod.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import static net.minecraft.client.gui.GuiScreen.setClipboardString;

public class TokenCommand extends Command {
    public TokenCommand() {
        super("Token", new String[]{});
    }

    @Override
    public void execute(String[] commands) {
        String kek = mc.session.getToken();
        Command.sendMessage("ur token is" + kek);
    }
}