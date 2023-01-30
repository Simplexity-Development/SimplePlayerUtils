package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.config.Defaults;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class HatCommand implements CommandExecutor, TabCompleter {
    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    HashSet<Material> blockedHats = Defaults.getHatBlacklist();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_ONLY_PLAYER.getMessage()));
            return false;
        }
        if (!player.hasPermission(SPUPerm.HAT.getPerm())) {
            sender.sendMessage(Util.messageParsing(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    null, null, null, null, null, null, null));
            return false;
        }
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        ItemStack handItem = player.getInventory().getItemInMainHand();
        ItemStack helmetItem = player.getInventory().getHelmet();
        if (helmetItem != null) {
            ItemMeta helmetMeta = helmetItem.getItemMeta();
            if (config.getBoolean("hat-respects-binding-enchant") && helmetMeta.hasEnchant(Enchantment.BINDING_CURSE)) {
                player.sendMessage(Util.messageParsing(SPUMessage.HAT_ERROR_BINDING.getMessage(),
                        null, null, null, null, null, null, null));
                return false;
            }
        }
        Material handItemType = handItem.getType();
        boolean whitelist = config.getBoolean("list-is-whitelist");
        if ((!whitelist && blockedHats.contains(handItemType)) ||
                (whitelist && !blockedHats.contains(handItemType))) {
            player.sendMessage(Util.messageParsing(SPUMessage.HAT_ERROR_BLOCKED_ITEM.getMessage(),
                    null, null, null, null, null, handItemType.toString().toLowerCase(Locale.ROOT), null));
            return false;
        }
        player.getInventory().setHelmet(handItem);
        player.getInventory().setItemInMainHand(helmetItem);
        player.sendMessage(Util.messageParsing(SPUMessage.HAT_OUTPUT.getMessage(),
                null, null, null, null, null, null, null));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
