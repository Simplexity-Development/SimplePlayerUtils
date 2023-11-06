package simplexity.simpleplayerutils.commands.inventories;

import simplexity.simpleplayerutils.commands.AbstractInventoryCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class AnvilCommand extends AbstractInventoryCommand {
    
    
    public AnvilCommand(Permission permission, Permission otherPermission, Sound sound, String successOnOther) {
        super(permission, otherPermission, sound, successOnOther);
    }
    
    @Override
    public void openInventory(CommandSender sender) {
        getPlayer().openAnvil(getPlayerLocation(), true);
        super.openInventory(sender);
    }
}
