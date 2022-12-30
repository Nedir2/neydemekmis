package me.cumhax.aehack.mod.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.cumhax.aehack.ae;
import me.cumhax.aehack.event.events.ClientEvent;
import me.cumhax.aehack.mod.command.Command;
import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.mod.setting.Bind;
import me.cumhax.aehack.mod.setting.Setting;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ClickGuiModule extends Module {
    public static final ClickGuiModule INSTANCE = new ClickGuiModule();
    public Setting<String> prefix = this.register(new Setting<>("Prefix", "!"));
    public Setting<Boolean> customFov = this.register(new Setting<>("CustomFov", false));
    public Setting<Float> fov = this.register(new Setting<>("Fov", 110f, -180f, 180f));
    public Setting<Integer> red = this.register(new Setting<>("Red", 116, 0, 255));
    public Setting<Integer> green = this.register(new Setting<>("Green", 85, 0, 255));
    public Setting<Integer> blue = this.register(new Setting<>("Blue", 185, 0, 255));
    public Setting<Integer> hoverAlpha = this.register(new Setting<>("Alpha", 255, 0, 255));
    public Setting<Integer> topRed = this.register(new Setting<>("SecondRed", 116, 0, 255));
    public Setting<Integer> topGreen = this.register(new Setting<>("SecondGreen", 85, 0, 255));
    public Setting<Integer> topBlue = this.register(new Setting<>("SecondBlue", 165, 0, 255));
    public Setting<Integer> alpha = this.register(new Setting<>("HoverAlpha", 240, 0, 255));
    public Setting<Integer> BGRed = this.register(new Setting<>("BGRed", 255, 0, 255));
    public Setting<Integer> BGGreen = this.register(new Setting<>("BGGreen", 202, 0, 255));
    public Setting<Integer> BGBlue = this.register(new Setting<>("BGBlue", 165, 0, 255));
    public Setting<Integer> BGalpha = this.register(new Setting<>("BGalpha", 150, 0, 255));
   // public Setting<RainbowMode> rainbowModeHud = this.register(new Setting<>("HRainbowMode", RainbowMode.Static, v -> this.rainbow.getValue()));
    //public Setting<RainbowModeArray> rainbowModeA = this.register(new Setting<>("ARainbowMode", RainbowModeArray.Static, v -> this.rainbow.getValue()));
    //public Setting<Integer> rainbowHue = this.register(new Setting<>("Delay", 240, 0, 600, v -> this.rainbow.getValue()));
    //public Setting<Float> rainbowBrightness = this.register(new Setting<>("Brightness ", 150f, 1f, 255f, v -> this.rainbow.getValue()));
    //public Setting<Float> rainbowSaturation = this.register(new Setting<>("Saturation", 150f, 1f, 255f, v -> this.rainbow.getValue()));

    public ClickGuiModule() {
        super("ClickGui", "Opens the ClickGui", Module.Category.CLIENT, true, false, false);
        this.bind.setValue(new Bind(Keyboard.KEY_J));
    }

    @Override
    public void onUpdate() {
        if (this.customFov.getValue()) ClickGuiModule.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, this.fov.getValue());
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                ae.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to " + ChatFormatting.DARK_GRAY + ae.commandManager.getPrefix());
            }
            ae.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(me.cumhax.aehack.mod.gui.ClickGui.getClickGui());
    }

    @Override
    public void onLoad() {
        ae.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        ae.commandManager.setPrefix(this.prefix.getValue());
    }

    @Override
    public void onTick() {
        if (!(ClickGuiModule.mc.currentScreen instanceof me.cumhax.aehack.mod.gui.ClickGui)) this.disable();
    }

    public enum RainbowModeArray {
        Static,
        Up
    }

    public enum RainbowMode {
        Static,
        Horjontile
    }
}

