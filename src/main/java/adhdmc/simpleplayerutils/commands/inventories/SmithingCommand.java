package adhdmc.simpleplayerutils.commands.inventories;

import adhdmc.simpleplayerutils.commands.AbstractInventoryCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class SmithingCommand extends AbstractInventoryCommand {
    
    
    public SmithingCommand(Permission permission, Permission otherPermission, Sound sound, String successOnOther) {
        super(permission, otherPermission, sound, successOnOther);
    }
    
    @Override
    public void openInventory(CommandSender sender) {
        getPlayer().openSmithingTable(getPlayerLocation(), true);
        super.openInventory(sender);
    }
}
