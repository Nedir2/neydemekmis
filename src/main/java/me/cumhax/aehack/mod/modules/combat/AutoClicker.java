package me.cumhax.aehack.mod.modules.combat;

import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.mod.setting.Setting;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

public class AutoClicker extends Module {
    private long lastClick;
    private long hold;

    private double speed;
    private double holdLength;
    public Setting<Boolean> Always = this.register(new Setting<>("Alwasys oin", false));

    public AutoClicker() {
        super("AutoClicker", "TestDesc", Category.COMBAT, true, false, false);
    }


    public void onUpdate() {
        if(Mouse.isButtonDown(0) || this.Always.getValue()) {
            if(System.currentTimeMillis() - lastClick > speed * 1) {
                lastClick = System.currentTimeMillis();
                if(hold < lastClick) {
                    hold = lastClick;
                }
                int key = mc.gameSettings.keyBindAttack.getKeyCode();
                KeyBinding.setKeyBindState(key, true);
                KeyBinding.onTick(key);
            } else if (System.currentTimeMillis() - hold > holdLength * 1) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
            }
        }
    }

}

