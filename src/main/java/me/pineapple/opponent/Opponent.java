package me.pineapple.opponent;

import me.pineapple.opponent.client.command.manage.CommandManager;
import me.pineapple.opponent.client.module.manage.ModuleManager;
import me.pineapple.opponent.client.other.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = Opponent.MOD_ID, name = Opponent.MOD_NAME, version = Opponent.VERSION)
public class Opponent {

    public static final String MOD_ID = "opponent";
    public static final String MOD_NAME = "opponent";
    public static final String VERSION = "v1.0.3";
    public static final Logger LOGGER = LogManager.getLogger("opponent");

    @Mod.Instance(MOD_ID)
    public static Opponent INSTANCE;

    private final File file = new File(Minecraft.getMinecraft().gameDir, "opponent");
    private final FontManager fontManager = new FontManager();
    private final ModuleManager moduleManager = new ModuleManager();
    private final CommandManager commandManager = new CommandManager();
    private final FriendManager friendManager = new FriendManager();
    private final SafetyManager safetyManager = new SafetyManager();
    private final ConfigManager configManager = new ConfigManager();
    private final SpeedManager speedManager = new SpeedManager();
    private final FileManager fileManager = new FileManager();
    private final PopManager popManager = new PopManager();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        moduleManager.init();
        friendManager.setDirectory(new File(file, "friends.json"));
        friendManager.init();
        commandManager.init();
        configManager.init();
        EventListener.INSTANCE.init();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            configManager.saveConfig();
            friendManager.unload();
        }));
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public SpeedManager getSpeedManager() {
        return speedManager;
    }

    public PopManager getPopManager() {
        return popManager;
    }

    public SafetyManager getSafetyManager() {
        return safetyManager;
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}
