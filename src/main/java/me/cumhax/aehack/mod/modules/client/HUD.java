package me.cumhax.aehack.mod.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.cumhax.aehack.ae;
import me.cumhax.aehack.event.events.ClientEvent;
import me.cumhax.aehack.event.events.Render2DEvent;
import me.cumhax.aehack.mod.modules.Module;
import me.cumhax.aehack.mod.setting.Setting;
import me.cumhax.aehack.utils.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUD extends Module {
    private static final ItemStack totem = new ItemStack(Items.TOTEM_OF_UNDYING);
    private static HUD INSTANCE = new HUD();
    private final Setting<Boolean> renderingUp = register(new Setting<>("RenderingUp", true, "Orientation of the HUD-Elements."));
    private final Setting<Boolean> waterMark = register(new Setting<>("Watermark", false, "displays watermark"));
    private final Setting<Boolean> arrayList = register(new Setting<>("ArrayList", true, "Lists the active modules."));
    private final Setting<Boolean> totems = register(new Setting<>("Totems", true, "TotemHUD"));
    private final Setting<Boolean> greeter = register(new Setting<>("Greeter", false, "The time"));
    public Setting<TextUtil.Color> bracketColor = register(new Setting<>("BracketColor", TextUtil.Color.WHITE));
    public Setting<TextUtil.Color> commandColor = register(new Setting<>("NameColor", TextUtil.Color.AQUA));
    public Setting<String> commandBracket = register(new Setting<>("Bracket", "["));
    public Setting<String> commandBracket2 = register(new Setting<>("OtherBracket", "]"));
    public Setting<Boolean> notifyToggles = register(new Setting<>("Notifcations", false, "notifys in chat when shit happens"));
    public Setting<Boolean> Dots = register(new Setting<>("Dots", true, "Kekw"));
    public Setting<RenderingMode> renderingMode = register(new Setting<>("Ordering", RenderingMode.Length));
    public static final String command = "ae";
    private int color;
    private boolean shouldIncrement;
    private int hitMarkerTimer;

    public HUD() {
        super("HUD", "HUD Elements rendered on your screen", Module.Category.CLIENT, true, false, false);
        this.enabled.setValue(true);
        this.drawn.setValue(false);
        setInstance();
    }

    public static HUD getInstance() {
        if (INSTANCE == null) INSTANCE = new HUD();
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public void onUpdate() {
        if (this.shouldIncrement) this.hitMarkerTimer++;
        if (this.hitMarkerTimer == 10) {
            this.hitMarkerTimer = 0;
            this.shouldIncrement = false;
        }
    }

    public void onRender2D(Render2DEvent event) {
        if (fullNullCheck()) return;
        int width = this.renderer.scaledWidth;
        int height = this.renderer.scaledHeight;
        this.color = ColorUtil.toRGBA(ClickGuiModule.INSTANCE.red.getValue(), ClickGuiModule.INSTANCE.green.getValue(), ClickGuiModule.INSTANCE.blue.getValue());
        if (this.waterMark.getValue()) {
            String string = command + " v" + ae.MODVER;
            int[] arrayOfInt = {1};
            char[] stringToCharArray = string.toCharArray();
            float f = 0.0F;
            for (char c : stringToCharArray) {
                f += this.renderer.getStringWidth(String.valueOf(c));
                arrayOfInt[0] = arrayOfInt[0] + 1;
                this.renderer.drawString(string, 2.0F, 2, this.color, true);
            }
        }
        int[] counter1 = {1};
        int j = (mc.currentScreen instanceof net.minecraft.client.gui.GuiChat && !this.renderingUp.getValue()) ? 14 : 0;
        if (this.arrayList.getValue()) if (this.renderingUp.getValue()) {
            if (this.renderingMode.getValue() == RenderingMode.ABC) {
                for (int k = 0; k < ae.moduleManager.sortedModulesABC.size(); k++) {
                    String str = ae.moduleManager.sortedModulesABC.get(k);
                    this.renderer.drawString(str, (width - 2 - this.renderer.getStringWidth(str)), (2 + j * 10), this.color, true);
                    j++;
                    counter1[0] = counter1[0] + 1;
                }
            } else {
                for (int k = 0; k < ae.moduleManager.sortedModules.size(); k++) {
                    Module module = ae.moduleManager.sortedModules.get(k);
                    String str = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                    this.renderer.drawString(str, (width - 2 - this.renderer.getStringWidth(str)), (2 + j * 10), this.color, true);
                    j++;
                    counter1[0] = counter1[0] + 1;
                }
            }
        } else if (this.renderingMode.getValue() == RenderingMode.ABC) {
            for (int k = 0; k < ae.moduleManager.sortedModulesABC.size(); k++) {
                String str = ae.moduleManager.sortedModulesABC.get(k);
                j += 10;
                this.renderer.drawString(str, (width - 2 - this.renderer.getStringWidth(str)), (height - j), this.color, true);
                counter1[0] = counter1[0] + 1;
            }
        } else {
            for (int k = 0; k < ae.moduleManager.sortedModules.size(); k++) {
                Module module = ae.moduleManager.sortedModules.get(k);
                String str = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                j += 10;
                this.renderer.drawString(str, (width - 2 - this.renderer.getStringWidth(str)), (height - j),  this.color, true);
                counter1[0] = counter1[0] + 1;
            }
        }
        String grayString = String.valueOf(ChatFormatting.GRAY);
        int i = (mc.currentScreen instanceof net.minecraft.client.gui.GuiChat && this.renderingUp.getValue()) ? 13 : (this.renderingUp.getValue() ? -2 : 0);
        if (this.renderingUp.getValue()) {
            String fpsText = grayString + "FPS " + ChatFormatting.WHITE + Minecraft.debugFPS;
            String str1 = grayString + "Ping " + ChatFormatting.WHITE + ae.serverManager.getPing();
            if (this.renderer.getStringWidth(str1) > this.renderer.getStringWidth(fpsText)) {
            }
        } else {
            String fpsText = grayString + "FPS " + ChatFormatting.WHITE + Minecraft.debugFPS;
            String str1 = grayString + "Ping " + ChatFormatting.WHITE + ae.serverManager.getPing();
            if (this.renderer.getStringWidth(str1) > this.renderer.getStringWidth(fpsText)) {
            }
        }
        boolean inHell = mc.world.getBiome(mc.player.getPosition()).getBiomeName().equals("Hell");
        int posX = (int) mc.player.posX;
        int posY = (int) mc.player.posY;
        int posZ = (int) mc.player.posZ;
        float nether = !inHell ? 0.125F : 8.0F;
        int hposX = (int) (mc.player.posX * nether);
        int hposZ = (int) (mc.player.posZ * nether);
        i = (mc.currentScreen instanceof net.minecraft.client.gui.GuiChat) ? 14 : 0;
        String coordinates = ChatFormatting.WHITE + "XYZ " + ChatFormatting.RESET + (inHell ? (posX + ", " + posY + ", " + posZ + ChatFormatting.WHITE + " [" + ChatFormatting.RESET + hposX + ", " + hposZ + ChatFormatting.WHITE + "]" + ChatFormatting.RESET) : (posX + ", " + posY + ", " + posZ + ChatFormatting.WHITE + " [" + ChatFormatting.RESET + hposX + ", " + hposZ + ChatFormatting.WHITE + "]"));
        i += 10;
        if (1 == 8) {
            if (1 == 8) {
            } else {
                int[] counter2 = {1};
                float s = 0.0F;
                int[] counter3 = {1};
                float u = 0.0F;
            }
        }
        if (this.totems.getValue()) renderTotemHUD();
        if (this.greeter.getValue()) renderGreeter();
    }

    public void renderGreeter() {
        int width = this.renderer.scaledWidth;
        String text = "";
        if (this.greeter.getValue()) text = text + MathUtil.getTimeOfDay() + mc.player.getDisplayNameString();
            this.renderer.drawString(text, width / 2.0F - this.renderer.getStringWidth(text) / 2.0F + 2.0F, 2.0F, this.color, true);
        }

    public void renderLag() {}

    public void renderTotemHUD() {
        int width = this.renderer.scaledWidth;
        int height = this.renderer.scaledHeight;
        int totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> (itemStack.getItem() == Items.TOTEM_OF_UNDYING)).mapToInt(ItemStack::getCount).sum();
        if (mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)
            totems += mc.player.getHeldItemOffhand().getCount();
        if (totems > 0) {
            GlStateManager.enableTexture2D();
            int i = width / 2;
            int y = height - 55; //- ((mc.player.isInWater() && mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
            int x = i - 189 + 180 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0F;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(totem, x, y);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, totem, x, y, "");
            RenderUtil.itemRender.zLevel = 0.0F;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(totems + "", (x + 19 - 2 - this.renderer.getStringWidth(totems + "")), (y + 9), 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(AttackEntityEvent event) {
        this.shouldIncrement = true;
    }

    public void onLoad() {
        ae.commandManager.setClientMessage(getCommandMessage());
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2 && equals(event.getSetting().getFeature()))
            ae.commandManager.setClientMessage(getCommandMessage());
    }

    public String getCommandMessage() {
        return TextUtil.coloredString(this.commandBracket.getPlannedValue(), this.bracketColor.getPlannedValue()) + TextUtil.coloredString(command, this.commandColor.getPlannedValue()) + TextUtil.coloredString(this.commandBracket2.getPlannedValue(), this.bracketColor.getPlannedValue());
    }

    public enum RenderingMode {
        Length, ABC
    }
}
