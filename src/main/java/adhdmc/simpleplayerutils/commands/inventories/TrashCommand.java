package adhdmc.simpleplayerutils.commands.inventories;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.commands.util.CommandOnOther;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.SPUSound;
import adhdmc.simpleplayerutils.util.Util;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TrashCommand implements TabExecutor {
    final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    private static final HashMap<UUID, Inventory> invMap = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player player = CommandOnOther.runCommandOnOtherPlayer(SPUPerm.TRASH_OTHER.getPerm(), sender, args);
            if (player == null) {
                return false;
            }
            openTrash(player, sender);
            return true;
        }
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        openTrash(player, null);
        return true;
    }

    private void openTrash(Player player, CommandSender sender){
        Location playerLocation = player.getLocation();
        Inventory trashInventory = Bukkit.createInventory(null, 36, miniMessage.deserialize(SPUMessage.TRASH_INVENTORY_NAME.getMessage()));
        player.openInventory(trashInventory);
        UUID playerUUID = player.getUniqueId();
        invMap.put(playerUUID, trashInventory);
        player.playSound(playerLocation, SPUSound.TRASH_SOUND.getSound(), 1, 1);
        if (sender != null) {
            sender.sendMessage(Util.parseTargetOnly(SPUMessage.TRASH_COMMAND_OTHER.getMessage(), player.displayName()));
        }
    }

    public static HashMap<UUID, Inventory> getInvMap() {
        return invMap;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
