package me.cumhax.aehack.mod.modules.misc;

import me.cumhax.aehack.mod.command.Command;
import me.cumhax.aehack.mod.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketChatMessage;

public class AutoKiller extends Module {
    public AutoKiller() {
        super("AutoKiller", "AutoKiller", Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.connection.sendPacket(new CPacketChatMessage("/Kill"));
        this.enabled.setValue(Boolean.FALSE);
    }
}

