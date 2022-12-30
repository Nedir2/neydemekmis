package me.cumhax.aehack.utils;

import net.minecraft.client.Minecraft;
import me.cumhax.aehack.ae;

public interface Globals {
    Minecraft mc = Minecraft.getMinecraft();

    default boolean fullNullCheck() {
        return mc.player != null && mc.world != null;
    }

    default ae getCrimp() {
        return ae.INSTANCE;
    }
}
