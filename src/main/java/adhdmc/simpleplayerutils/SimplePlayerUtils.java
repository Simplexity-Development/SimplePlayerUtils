package adhdmc.simpleplayerutils;

import adhdmc.simpleplayerutils.commands.*;
import adhdmc.simpleplayerutils.commands.inventories.*;
import adhdmc.simpleplayerutils.config.Defaults;
import adhdmc.simpleplayerutils.config.LocaleBuilder;
import adhdmc.simpleplayerutils.listeners.AFKListener;
import adhdmc.simpleplayerutils.listeners.ChatListener;
import adhdmc.simpleplayerutils.listeners.FlyListeners;
import adhdmc.simpleplayerutils.listeners.InventoryCloseListener;
import adhdmc.simpleplayerutils.util.SPUExpansion;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.SPUSound;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class SimplePlayerUtils extends JavaPlugin {
    
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    //todo:Add regex for /rename
    //todo:Add lore command
    private static SimplePlayerUtils instance;
    private boolean purpurLoaded = true;
    private boolean papiEnabled = true;
    
    public static Logger SPULogger() {
        return SimplePlayerUtils.getInstance().getLogger();
    }
    
    public static SimplePlayerUtils getInstance() {
        return instance;
    }
    
    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
    
    public static Logger getSPULogger() {
        return getInstance().getLogger();
    }
    
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
    
    private void registerConfigs() {
        Defaults.setConfigDefaults();
        saveDefaultConfig();
        Defaults.fillBlacklists();
        SPUSound.setConfiguredSounds();
        LocaleBuilder.getInstance();
    }
    
    private void registerPurpurClasses() {
        this.getServer().getPluginManager().registerEvents(new AFKListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        Objects.requireNonNull(this.getCommand("afk")).setExecutor(new AFKCommand());
    }
    
    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new FlyListeners(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
    }
    
    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(this.getCommand("craft")).setExecutor(new CraftCommand(
                SPUPerm.CRAFTING.getPerm(), SPUPerm.CRAFTING_OTHER.getPerm(),
                SPUSound.CRAFTING_SOUND.getSound(), SPUMessage.CRAFT_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("anvil")).setExecutor(new AnvilCommand(
                SPUPerm.ANVIL.getPerm(), SPUPerm.ANVIL_OTHER.getPerm(),
                SPUSound.ANVIL_SOUND.getSound(), SPUMessage.ANVIL_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("cartography")).setExecutor(new CartographyCommand(
                SPUPerm.CARTOGRAPHY.getPerm(), SPUPerm.CARTOGRAPHY_OTHER.getPerm(),
                SPUSound.CARTOGRAPHY_SOUND.getSound(), SPUMessage.CARTOGRAPHY_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("grindstone")).setExecutor(new GrindstoneCommand(
                SPUPerm.GRINDSTONE.getPerm(), SPUPerm.GRINDSTONE_OTHER.getPerm(),
                SPUSound.GRINDSTONE_SOUND.getSound(), SPUMessage.GRINDSTONE_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("loom")).setExecutor(new LoomCommand(
                SPUPerm.LOOM.getPerm(), SPUPerm.LOOM_OTHER.getPerm(),
                SPUSound.LOOM_SOUND.getSound(), SPUMessage.LOOM_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("smithing")).setExecutor(new SmithingCommand(
                SPUPerm.SMITHING.getPerm(), SPUPerm.SMITHING_OTHER.getPerm(),
                SPUSound.SMITHING_SOUND.getSound(), SPUMessage.SMITHING_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("stonecutter")).setExecutor(new StonecutterCommand(
                SPUPerm.STONECUTTER.getPerm(), SPUPerm.STONECUTTER_OTHER.getPerm(),
                SPUSound.STONECUTTER_SOUND.getSound(), SPUMessage.STONECUTTER_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(new EnderchestCommand(
                SPUPerm.ENDERCHEST.getPerm(), SPUPerm.ENDERCHEST_OTHER.getPerm(),
                SPUSound.ENDERCHEST_SOUND.getSound(), SPUMessage.ENDERCHEST_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("flyspeed")).setExecutor(new FlyspeedCommand());
        Objects.requireNonNull(this.getCommand("walkspeed")).setExecutor(new WalkspeedCommand());
        Objects.requireNonNull(this.getCommand("rename")).setExecutor(new RenameCommand());
        Objects.requireNonNull(this.getCommand("hat")).setExecutor(new HatCommand());
        Objects.requireNonNull(this.getCommand("trash")).setExecutor(new TrashCommand(
                SPUPerm.TRASH.getPerm(), SPUPerm.TRASH_OTHER.getPerm(),
                SPUSound.TRASH_SOUND.getSound(), SPUMessage.TRASH_COMMAND_OTHER.getMessage()
        ));
        Objects.requireNonNull(this.getCommand("repair"));
        Objects.requireNonNull(this.getCommand("spureload")).setExecutor(new SPUReload());
    }
    
    public boolean isPapiEnabled() {
        return papiEnabled;
    }
    
    public void reloadConfigs() {
        getInstance().reloadConfig();
        Defaults.fillBlacklists();
        SPUSound.setConfiguredSounds();
        LocaleBuilder.getInstance().reloadLocale();
    }
}
