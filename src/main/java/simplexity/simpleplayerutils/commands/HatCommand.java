package simplexity.simpleplayerutils.commands;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import simplexity.simpleplayerutils.config.Defaults;
import simplexity.simpleplayerutils.util.SPUMessage;
import simplexity.simpleplayerutils.util.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class HatCommand implements TabExecutor {
    
    final HashSet<Material> blockedHats = Defaults.getHatBlacklist();
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        ItemStack handItem = player.getInventory().getItemInMainHand();
        ItemStack helmetItem = player.getInventory().getHelmet();
        if (helmetItem != null && config.getBoolean("hat-respects-binding-enchant") && helmetItem.getItemMeta().hasEnchant(Enchantment.BINDING_CURSE)) {
            player.sendMessage(Util.parsePrefixOnly(SPUMessage.HAT_ERROR_BINDING.getMessage()));
            return false;
        }
        Material handItemType = handItem.getType();
        boolean whitelist = config.getBoolean("list-is-whitelist");
        if ((!whitelist && blockedHats.contains(handItemType)) || (whitelist && !blockedHats.contains(handItemType))) {
            player.sendMessage(Util.parseItem(SPUMessage.HAT_ERROR_BLOCKED_ITEM.getMessage(), handItemType.toString()));
            return false;
        }
        player.getInventory().setHelmet(handItem);
        player.getInventory().setItemInMainHand(helmetItem);
        player.sendMessage(Util.parsePrefixOnly(SPUMessage.HAT_OUTPUT.getMessage()));
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
