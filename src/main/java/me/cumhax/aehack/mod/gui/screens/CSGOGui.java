package me.cumhax.aehack.mod.gui.screens;

import me.cumhax.aehack.utils.RenderUtil;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class CSGOGui extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(20 , 20 , 200, 200, new Color(60, 60, 60, 255).getRGB());
    }
}



