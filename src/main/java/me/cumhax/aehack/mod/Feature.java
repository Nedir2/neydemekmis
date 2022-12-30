package me.cumhax.aehack.mod;

import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.gui.ClickGui;
import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.mod.setting.Setting;
import me.cumhax.aehack.managers.TextManager;
import me.cumhax.aehack.utils.Util;

import java.util.ArrayList;
import java.util.List;

public abstract class Feature implements Util {
    public List<Setting<?>> settings = new ArrayList<>();
    public TextManager renderer = ae.textManager;
    private String name;

    public Feature() {
    }

    public Feature(String name) {
        this.name = name;
    }

    public static boolean nullCheck() {
        return Feature.mc.player == null;
    }

    public static boolean fullNullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null;
    }

    public String getName() {
        return this.name;
    }

    public List<Setting<?>> getSettings() {
        return this.settings;
    }

    public boolean isEnabled() {
        if (this instanceof Module) {
            return ((Module) this).isOn();
        }
        return false;
    }

    public <T> Setting<T> register(Setting<T> setting) {
        setting.setFeature(this);
        this.settings.add(setting);
        if (this instanceof Module && Feature.mc.currentScreen instanceof ClickGui) {
            ClickGui.getInstance().updateModule((Module) this);
        }
        return setting;
    }

    public Setting<?> getSettingByName(String name) {
        for (Setting<?> setting : this.settings) {
            if (!setting.getName().equalsIgnoreCase(name)) continue;
            return setting;
        }
        return null;
    }

    public void reset() {
        for (Setting setting : this.settings) setting.setValue(setting.getDefaultValue());
    }
}

