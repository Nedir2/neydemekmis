package me.cumhax.aehack;

//import me.cumhax.aehack.CapeShit.CapeManager;
import me.cumhax.aehack.mod.modules.client.RPC;
import me.cumhax.aehack.managers.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = ae.MODID, name = ae.MODNAME, version = ae.MODVER)
public class ae {
    public static final String MODID = "aehack+";
    public static final String MODNAME = "aehack++";
    public static final String MODVER = "1.0";
    public static final Logger LOGGER = LogManager.getLogger("aehack++");
    public static CommandManager commandManager;
    public static ModuleManager moduleManager;
    public static ColorManager colorManager;
    public static HoleManager holeManager;
    public static InventoryManager inventoryManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ReloadManager reloadManager;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static TextManager textManager;
    @Mod.Instance
    public static ae INSTANCE;
    private static boolean unloaded;

    static {
        unloaded = false;
    }

    public static void load() {
        LOGGER.info("Loading aehack+ by CumHax");
        unloaded = false;
        if (reloadManager != null) {
            reloadManager.unload();
            reloadManager = null;
        }
        textManager = new TextManager();
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        rotationManager = new RotationManager();
        eventManager = new EventManager();
        speedManager = new SpeedManager();
        inventoryManager = new InventoryManager();
        serverManager = new ServerManager();
        fileManager = new FileManager();
        colorManager = new ColorManager();
        positionManager = new PositionManager();
        configManager = new ConfigManager();
        holeManager = new HoleManager();
        LOGGER.info("Managers loaded.");
        moduleManager.init();
        LOGGER.info("Modules loaded.");
        configManager.init();
        eventManager.init();
        LOGGER.info("EventManager loaded.");
        textManager.init(true);
        moduleManager.onLoad();
        LOGGER.info("aehack++ successfully loaded!\n");
    }

    public static void unload(boolean unload) {
        LOGGER.info("\n\nUnloading aehack+ by cumhax");
        if (unload) {
            reloadManager = new ReloadManager();
            reloadManager.init(commandManager != null ? commandManager.getPrefix() : "!");
        }
        ae.onUnload();
        eventManager = null;
        speedManager = null;
        holeManager = null;
        positionManager = null;
        rotationManager = null;
        configManager = null;
        commandManager = null;
        colorManager = null;
        serverManager = null;
        fileManager = null;
        inventoryManager = null;
        moduleManager = null;
        textManager = null;
        //cape = null;
        LOGGER.info("aehack++ unloaded!\n");
    }

    public static void reload() {
        ae.unload(false);
        ae.load();
    }

    public static void onUnload() {
        if (!unloaded) {
            eventManager.onUnload();
            moduleManager.onUnload();
            configManager.saveConfig(ae.configManager.config.replaceFirst("aehackplus/", ""));
            moduleManager.onUnloadPost();
            unloaded = true;
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("fuck femboys - CumHax");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Display.setTitle("Minecraft 1.12.2");
        ae.load();
        }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (moduleManager.getModuleByClass(RPC.class).isEnabled()) {
            DiscordPresence.stop();
            DiscordPresence.start();
            Display.setTitle("Minecraft 1.12.2");
        }
}
}

