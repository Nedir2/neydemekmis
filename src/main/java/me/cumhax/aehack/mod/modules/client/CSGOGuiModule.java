package me.cumhax.aehack.mod.modules.client;

import me.cumhax.aehack.mod.gui.screens.CSGOGui;
import me.cumhax.aehack.mod.modules.Module;

public class CSGOGuiModule extends Module {
    public CSGOGuiModule() {
        super("CSGOGui", "Opens a  CSGO style GUI", Category.CLIENT, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new CSGOGui());
    }

    @Override
    public void onDisable() {mc.displayGuiScreen(new CSGOGui());}
}
