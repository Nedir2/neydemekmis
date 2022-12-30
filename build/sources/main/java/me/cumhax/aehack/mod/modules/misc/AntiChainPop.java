package me.cumhax.aehack.mod.modules.misc;

import me.cumhax.aehack.mod.modules.Module;

public class AntiChainPop extends Module {
    public AntiChainPop() {
        super("AntiChainPop", "AntiChainPop", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.player.getHealth() == 1) {mc.world.sendQuittingDisconnectingPacket();}
    }
}