package adhdmc.simpleplayerutils;

import adhdmc.simpleplayerutils.commands.*;
import adhdmc.simpleplayerutils.commands.inventories.*;
import adhdmc.simpleplayerutils.listeners.AFKListener;
import adhdmc.simpleplayerutils.listeners.FlyListeners;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePlayerUtils extends JavaPlugin {
    private static SimplePlayerUtils instance;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new FlyListeners(), this);
        this.getServer().getPluginManager().registerEvents(new AFKListener(), this);
        registerCommands();
    }

    public static SimplePlayerUtils getInstance() {
        return instance;
    }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
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
        this.getCommand("afk").setExecutor(new AFKCommand());
    }
}
