package me.cumhax.aehack.mod.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.command.Command;
import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.mod.setting.Bind;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {
    public BindCommand() {
        super("bind", new String[]{"<module>", "<bind>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            BindCommand.sendMessage("Please specify a module.");
            return;
        }
        String rkey = commands[1];
        String moduleName = commands[0];
        Module module = ae.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            BindCommand.sendMessage("Unknown module '" + module + "'!");
            return;
        }
        if (rkey == null) {
            BindCommand.sendMessage(module.getName() + " is bound to " + ChatFormatting.GRAY + module.getBind().toString());
            return;
        }
        int key = Keyboard.getKeyIndex(rkey.toUpperCase());
        if (rkey.equalsIgnoreCase("none")) {
            key = -1;
        }
        if (key == 0) {
            BindCommand.sendMessage("Unknown key '" + rkey + "'!");
            return;
        }
        module.bind.setValue(new Bind(key));
        BindCommand.sendMessage("Bind for " + module.getName() + " set to " + rkey.toUpperCase());
    }
}

