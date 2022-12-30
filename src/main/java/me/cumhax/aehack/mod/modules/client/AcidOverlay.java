package me.cumhax.aehack.mod.modules.client;

import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.utils.Util;
import net.minecraft.util.ResourceLocation;

public class AcidOverlay extends Module {

    public AcidOverlay() {
        super("AcidOverlay", "Pill In ibiza", Category.RENDER, true, false, false);
    }


    @Override
    public void onEnable() {
        if (this.mc.world != null) {
            Util.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/art.json"));
        }
    }

    @Override
    public void onDisable() {
        if (this.mc.world != null) {
            Util.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
}
