package adhdmc.simpleplayerutils.listeners;

import adhdmc.simpleplayerutils.commands.inventories.TrashCommand;
import adhdmc.simpleplayerutils.config.Defaults;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUSound;
import adhdmc.simpleplayerutils.util.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class InventoryCloseListener implements Listener {
    
    final HashMap<UUID, Inventory> inventoryHashMap = TrashCommand.getInvMap();
    
    final HashSet<Material> blacklistedTrash = Defaults.getTrashBlacklist();
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    
    public void onInventoryClose(InventoryCloseEvent closeEvent) {
        UUID playerUUID = closeEvent.getPlayer().getUniqueId();
        Inventory closedInventory = closeEvent.getInventory();
        if (!inventoryHashMap.containsKey(playerUUID)) return;
        if (!inventoryHashMap.containsValue(closedInventory)) return;
        Inventory trashInventory = inventoryHashMap.get(playerUUID);
        if (trashInventory.isEmpty()) return;
        Player player = (Player) closeEvent.getPlayer();
        ItemStack[] trashContents = trashInventory.getContents();
        Location playerLocation = player.getLocation();
        ArrayList<ItemStack> blockedItemsToDrop = new ArrayList<>();
        //check if any items that are in the trash are blacklisted, if they are, add to arraylist
        for (ItemStack item : trashContents) {
            if (item == null) {
                continue;
            }
            Material itemType = item.getType();
            if (!blacklistedTrash.contains(itemType)) {
                continue;
            }
            blockedItemsToDrop.add(item);
        }
        //Clear first so that no items get duplicated
        trashInventory.clear();
        //if the list isn't empty, drop the things
        if (!blockedItemsToDrop.isEmpty()) {
            for (ItemStack item : blockedItemsToDrop) {
                playerLocation.getWorld().dropItem(playerLocation, item);
            }
            player.sendMessage(Util.parsePrefixOnly(SPUMessage.TRASH_BLACKLIST_ITEMS_DROPPED.getMessage()));
            player.playSound(playerLocation, SPUSound.TRASH_ALERT.getSound(), 0.5f, 1);
        } else {
            closeEvent.getPlayer().sendMessage(Util.parsePrefixOnly(SPUMessage.TRASH_COMMAND_FEEDBACK.getMessage()));
        }
        
    }
}
