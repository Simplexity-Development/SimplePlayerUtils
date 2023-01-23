package adhdmc.simpleplayerutils;

import adhdmc.simpleplayerutils.commands.*;
import adhdmc.simpleplayerutils.commands.inventories.*;
import adhdmc.simpleplayerutils.config.LocaleBuilder;
import adhdmc.simpleplayerutils.listeners.AFKListener;
import adhdmc.simpleplayerutils.listeners.ChatListener;
import adhdmc.simpleplayerutils.listeners.FlyListeners;
import adhdmc.simpleplayerutils.config.Defaults;
import adhdmc.simpleplayerutils.listeners.InventoryCloseListener;
import adhdmc.simpleplayerutils.util.SPUExpansion;
import adhdmc.simpleplayerutils.util.SPUSound;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SimplePlayerUtils extends JavaPlugin {
    //todo:Add regex for /rename
    //todo:Add lore command
    private static SimplePlayerUtils instance;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private boolean purpurLoaded = true;
    private boolean papiEnabled = true;

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
        registerCommands();
        registerConfigs();
        try {
            Class.forName("org.purpurmc.purpur.event.PlayerAFKEvent");
        } catch (ClassNotFoundException e) {
            this.getLogger().severe("Purpur classes required for purpur-specific functions were not found on your server. The plugin may not perform as intended. Please use updated purpur for intended results.");
            purpurLoaded = false;
        }
        papiEnabled = this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
        if (purpurLoaded) {
            registerPurpurClasses();
        }
        if (purpurLoaded && papiEnabled) {
            new SPUExpansion().register();
        } else {
            this.getLogger().severe("Both purpur and placeholder API are needed for Simple Player Utils placeholders, placeholders will be unusable until both of these are present");
        }
    }
    public static Logger SPULogger() {
        return SimplePlayerUtils.getInstance().getLogger();
    }

    public static SimplePlayerUtils getInstance() {
        return instance;
    }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }

    public boolean isPapiEnabled() {
        return papiEnabled;
    }

    private void registerConfigs() {
        Defaults.setConfigDefaults();
        saveDefaultConfig();
        Defaults.fillBlacklist();
        SPUSound.setConfiguredSounds();
        LocaleBuilder.getInstance();
    }

    private void registerPurpurClasses(){
        this.getServer().getPluginManager().registerEvents(new AFKListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getCommand("afk").setExecutor(new AFKCommand());
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new FlyListeners(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
    }

    private void registerCommands() {
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("craft").setExecutor(new CraftCommand());
        this.getCommand("anvil").setExecutor(new AnvilCommand());
        this.getCommand("cartography").setExecutor(new CartographyCommand());
        this.getCommand("grindstone").setExecutor(new GrindstoneCommand());
        this.getCommand("loom").setExecutor(new LoomCommand());
        this.getCommand("smithing").setExecutor(new SmithingCommand());
        this.getCommand("stonecutter").setExecutor(new StonecutterCommand());
        this.getCommand("enderchest").setExecutor(new EnderchestCommand());
        this.getCommand("flyspeed").setExecutor(new FlyspeedCommand());
        this.getCommand("walkspeed").setExecutor(new WalkspeedCommand());
        this.getCommand("rename").setExecutor(new RenameCommand());
        this.getCommand("hat").setExecutor(new HatCommand());
        this.getCommand("trash").setExecutor(new TrashCommand());
        this.getCommand("spureload").setExecutor(new SPUReload());
    }
}
