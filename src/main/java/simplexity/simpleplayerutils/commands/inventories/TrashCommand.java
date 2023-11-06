package simplexity.simpleplayerutils.commands.inventories;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import simplexity.simpleplayerutils.commands.AbstractInventoryCommand;
import simplexity.simpleplayerutils.util.SPUMessage;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.permissions.Permission;

import java.util.HashMap;
import java.util.UUID;

public class TrashCommand extends AbstractInventoryCommand {
    
    private static final HashMap<UUID, Inventory> invMap = new HashMap<>();
    final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    
    public TrashCommand(Permission permission, Permission otherPermission, Sound sound, String successOnOther) {
        super(permission, otherPermission, sound, successOnOther);
    }
    
    public static HashMap<UUID, Inventory> getInvMap() {
        return invMap;
    }
    
    public void openInventory(CommandSender sender) {
        Inventory trashInventory = Bukkit.createInventory(null, 36, miniMessage.deserialize(SPUMessage.TRASH_INVENTORY_NAME.getMessage()));
        getPlayer().openInventory(trashInventory);
        UUID playerUUID = getPlayer().getUniqueId();
        invMap.put(playerUUID, trashInventory);
    }
    
    
}
