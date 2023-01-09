package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RenameCommand implements CommandExecutor, TabCompleter {
    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Console cannot run this
        if (!(sender instanceof Player player)) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_ONLY_PLAYER.getMessage()));
            return false;
        }
        //Check perms
        if ((player.hasPermission(SPUPerm.RENAME_BASIC.getPerm()) || player.hasPermission(SPUPerm.RENAME_MINIMESSAGE.getPerm()))) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            return false;
        }
        String renameString = String.join(" ", Arrays.stream(args).skip(0).collect(Collectors.joining(" ")));
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        ItemMeta heldItemMeta = heldItem.getItemMeta();
        Component currentItemName;
        if (heldItemMeta.hasDisplayName()) {
            currentItemName = heldItemMeta.displayName();
        } else {
            currentItemName = miniMessage.deserialize(heldItem.getType().toString());
        }
        Component newItemName;
        if (player.hasPermission(SPUPerm.RENAME_MINIMESSAGE.getPerm())) {
            newItemName = miniMessage.deserialize(renameString);
            heldItemMeta.displayName(newItemName);
            heldItem.setItemMeta(heldItemMeta);
            player.sendMessage(miniMessage.deserialize(SPUMessage.RENAME_COMMAND_FEEDBACK.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                    Placeholder.component("oldname", currentItemName),
                    Placeholder.component("newname", newItemName)));
            return true;
        }
        if (player.hasPermission(SPUPerm.RENAME_BASIC.getPerm())) {
            String strippedInput = miniMessage.stripTags(renameString);
            newItemName = miniMessage.deserialize(strippedInput);
            heldItemMeta.displayName(newItemName);
            heldItem.setItemMeta(heldItemMeta);
            player.sendMessage(miniMessage.deserialize(SPUMessage.RENAME_COMMAND_FEEDBACK.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                    Placeholder.component("oldname", currentItemName),
                    Placeholder.component("newname", newItemName)));
            return true;
        }
        player.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_GENERAL.getMessage(),
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
