package adhdmc.simpleplayerutils;

import adhdmc.simpleplayerutils.commands.*;
import adhdmc.simpleplayerutils.commands.inventories.*;
import adhdmc.simpleplayerutils.listeners.AFKListener;
import adhdmc.simpleplayerutils.listeners.FlyListeners;
import adhdmc.simpleplayerutils.util.SPUExpansion;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePlayerUtils extends JavaPlugin {
    private static SimplePlayerUtils instance;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private boolean purpurLoaded = true;
    private boolean papiEnabled = true;

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new FlyListeners(), this);
        registerCommands();
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

    public static SimplePlayerUtils getInstance() {
        return instance;
    }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }

    public boolean isPapiEnabled() {
        return papiEnabled;
    }

    private void registerPurpurClasses(){
        this.getServer().getPluginManager().registerEvents(new AFKListener(), this);
        this.getCommand("afk").setExecutor(new AFKCommand());
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

    }
}
