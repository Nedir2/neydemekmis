package me.cumhax.aehack.mod.modules.movement;

import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.mod.setting.Setting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Efly extends Module {
    public Setting<Float> speed = register(new Setting<>("speed", 1.8f, 0f, 3f, ""));
    public Efly() {
        super("ElytraFly", "Efly", Category.MOVEMENT, true, false, false);
    }


    public void onUpdate(){
        if(mc.player.capabilities.isFlying || mc.player.isElytraFlying())
            mc.player.setSprinting(false);
        if (mc.player.capabilities.isFlying) {
            mc.player.setVelocity(0, 0, 0);
            mc.player.setPosition(mc.player.posX, mc.player.posY - 0.000050000002f, mc.player.posZ);
            mc.player.capabilities.setFlySpeed((float)speed.getValue());
            mc.player.setSprinting(false);
        }

        if (mc.player.onGround) {
            mc.player.capabilities.allowFlying = false;
        }

        if (mc.player.isElytraFlying()) {
            mc.player.capabilities.setFlySpeed(.915f);
            mc.player.capabilities.isFlying = true;

            if (!mc.player.capabilities.isCreativeMode)
                mc.player.capabilities.allowFlying = true;
        }
    }

    public void onDisable() {
        mc.player.capabilities.isFlying = false;
        mc.player.capabilities.setFlySpeed(0.05f);
        if (!mc.player.capabilities.isCreativeMode)
            mc.player.capabilities.allowFlying = false;
    }
}