package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.config.Defaults;
import adhdmc.simpleplayerutils.config.LocaleBuilder;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.SPUSound;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SPUReload implements CommandExecutor, TabCompleter {
    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(SPUPerm.RELOAD.getPerm())) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            return false;
        }
        SimplePlayerUtils.getInstance().reloadConfig();
        LocaleBuilder.getInstance().reloadConfig();
        LocaleBuilder.getInstance().loadLocaleMessages();
        Defaults.fillBlacklists();
        SPUSound.setConfiguredSounds();
        sender.sendMessage(miniMessage.deserialize(SPUMessage.CONFIG_RELOADED.getMessage(),
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
