package me.cumhax.aehack.mod.gui.components.items.buttons;

import me.cumhax.aehack.ae;
import me.cumhax.aehack.mod.gui.ClickGui;
import me.cumhax.aehack.mod.gui.components.Component;
import me.cumhax.aehack.mod.gui.components.items.Item;
import me.cumhax.aehack.mod.modules.client.ClickGuiModule;
import me.cumhax.aehack.utils.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class Button extends Item {
    private boolean state;

    public Button(String name) {
        super(name);
        this.height = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(this.x, this.y, this.x + (float) this.width, this.y + (float) this.height - 0.5f, this.getState() ? (!this.isHovering(mouseX, mouseY) ? ae.colorManager.getColorWithAlpha(ae.moduleManager.getModuleByClass(ClickGuiModule.class).hoverAlpha.getValue()) : ae.colorManager.getColorWithAlpha(ae.moduleManager.getModuleByClass(ClickGuiModule.class).alpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        ae.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - (float) ClickGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.onMouseClick();
        }
    }

    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }

    public void toggle() {
    }

    public boolean getState() {
        return this.state;
    }

    @Override
    public int getHeight() {
        return 14;
    }

    public boolean isHovering(int mouseX, int mouseY) {
        for (Component component : ClickGui.getClickGui().getComponents()) {
            if (!component.drag) continue;
            return false;
        }
        return (float) mouseX >= this.getX() && (float) mouseX <= this.getX() + (float) this.getWidth() && (float) mouseY >= this.getY() && (float) mouseY <= this.getY() + (float) this.height;
    }
}

