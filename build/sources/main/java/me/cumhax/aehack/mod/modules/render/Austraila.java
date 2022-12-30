package me.cumhax.aehack.mod.modules.render;

import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.utils.Util;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class Austraila extends Module {
    public Austraila() {
        super("Austraila", "Austraila", Category.RENDER, true, false, false);
    }

    int Fov = -110;

    @Override
    public void onEnable() {
        if (this.mc.world != null) {
            Util.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/art.json"));
        }
        mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, this.Fov);
    }
    int Fov2 = 110;

    @Override
    public void onDisable() {
        mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, this.Fov2);
        if (this.mc.world != null) {
            Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
}
