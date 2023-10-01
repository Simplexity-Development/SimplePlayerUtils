package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RenameCommand implements TabExecutor {
    
    final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Console cannot run this
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        String renameString = String.join(" ", Arrays.stream(args).skip(0).collect(Collectors.joining(" ")));
        String strippedInput = miniMessage.stripTags(renameString);
        int maxChars = SimplePlayerUtils.getInstance().getConfig().getInt("rename-max-characters");
        if ((strippedInput.length() > maxChars) && !player.hasPermission(SPUPerm.RENAME_MAX_CHAR_BYPASS.getPerm())) {
            player.sendMessage(Util.parseSingleValueOnly(SPUMessage.RENAME_ERROR_INPUT_TOO_LONG.getMessage(), String.valueOf(maxChars)));
            return false;
        }
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        Component newItemName;
        
        if (player.hasPermission(SPUPerm.RENAME_MINIMESSAGE.getPerm())) {
            newItemName = miniMessage.deserialize(renameString).decoration(TextDecoration.ITALIC, false);
            setItemName(newItemName, heldItem);
            player.sendMessage(Util.parsePlayerInput(SPUMessage.RENAME_COMMAND_FEEDBACK.getMessage(), renameString));
            return true;
        }
        if (player.hasPermission(SPUPerm.RENAME_BASIC.getPerm())) {
            newItemName = miniMessage.deserialize(strippedInput);
            setItemName(newItemName, heldItem);
            player.sendMessage(Util.parsePlayerInput(SPUMessage.RENAME_COMMAND_FEEDBACK.getMessage(), strippedInput));
            return true;
        }
        player.sendMessage(Util.parsePrefixOnly(SPUMessage.ERROR_GENERAL.getMessage()));
        return false;
    }
    
    private void setItemName(Component itemNameToSet, ItemStack item) {
        ItemMeta editItemMeta = item.getItemMeta();
        editItemMeta.displayName(itemNameToSet);
        item.setItemMeta(editItemMeta);
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
