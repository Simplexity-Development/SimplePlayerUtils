package simplexity.simpleplayerutils.commands;

import simplexity.simpleplayerutils.util.SPUMessage;
import simplexity.simpleplayerutils.util.Util;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractInventoryCommand implements CommandExecutor, PlayerUtilsInterface {
    
    private final Permission permission;
    private final Permission adminPermission;
    private final Sound sound;
    private final String successOnOther;
    private Player player;
    private Location playerLocation;
    
    public AbstractInventoryCommand(Permission permission, Permission otherPermission, Sound sound, String successOnOther) {
        this.permission = permission;
        this.adminPermission = otherPermission;
        this.sound = sound;
        this.successOnOther = successOnOther;
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player) && args.length == 0) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        } else if (!(sender instanceof Player)) {
            player = PlayerUtilsInterface.checkAdminPerms(adminPermission, sender, args);
            if (player == null) return false;
        }
        if (args.length > 0) {
            player = PlayerUtilsInterface.checkAdminPerms(adminPermission, sender, args);
            if (player == null) return false;
        }
        if (args.length == 0) {
            player = (Player) sender;
            playerLocation = player.getLocation();
            player.playSound(playerLocation, sound, 1, 1);
            openInventory(null);
            return true;
        }
        playerLocation = player.getLocation();
        player.playSound(playerLocation, sound, 1, 1);
        openInventory(sender);
        return false;
    }
    
    public void openInventory(CommandSender sender) {
        if (sender != null) {
            sender.sendMessage(Util.parseTargetOnly(successOnOther, player.displayName()));
        }
    }
    
    
    
    public Permission getPermission() {
        return permission;
    }
    
    public Permission getAdminPermission() {
        return adminPermission;
    }
    
    public Sound getSound() {
        return sound;
    }
    
    
    public Player getPlayer() {
        return player;
    }
    
    public Location getPlayerLocation() {
        return playerLocation;
    }
    
    public String getSuccessOnOther() {
        return successOnOther;
    }
}
