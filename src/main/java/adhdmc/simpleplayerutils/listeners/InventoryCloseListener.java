package adhdmc.simpleplayerutils.listeners;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.commands.inventories.TrashCommand;
import adhdmc.simpleplayerutils.config.Defaults;
import adhdmc.simpleplayerutils.util.SPUMessage;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class InventoryCloseListener implements Listener {

    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    HashMap<UUID, Inventory> inventoryHashMap = TrashCommand.getInvMap();

    HashSet<Material> blacklistedTrash = Defaults.getTrashBlacklist();

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)

    public void onInventoryClose(InventoryCloseEvent closeEvent) {
        UUID playerUUID = closeEvent.getPlayer().getUniqueId();
        Inventory closedInventory = closeEvent.getInventory();
        if (!inventoryHashMap.containsKey(playerUUID)) return;
        if (!inventoryHashMap.containsValue(closedInventory)) return;
        Inventory trashInventory = inventoryHashMap.get(playerUUID);
        if (trashInventory.isEmpty()) return;
        trashInventory.clear();
        closeEvent.getPlayer().sendMessage(miniMessage.deserialize(SPUMessage.TRASH_COMMAND_FEEDBACK.getMessage(),
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));

    }
}
