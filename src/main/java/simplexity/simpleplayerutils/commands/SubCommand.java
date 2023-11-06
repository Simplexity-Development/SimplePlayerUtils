package simplexity.simpleplayerutils.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {
    private final String helpMessage;
    private final String permission;
    
    
    protected SubCommand(String helpMessage, String permission) {
        this.helpMessage = helpMessage;
        this.permission = permission;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public String getHelpMessage() {
        return helpMessage;
    }
    
    public abstract void execute(CommandSender sender, String[] args);
    public abstract List<String> subCommandTabCompletions(CommandSender sender);
    
}
