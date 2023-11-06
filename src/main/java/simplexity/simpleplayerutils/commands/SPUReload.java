package simplexity.simpleplayerutils.commands;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import simplexity.simpleplayerutils.util.SPUMessage;
import simplexity.simpleplayerutils.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SPUReload implements TabExecutor {
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        SimplePlayerUtils.getInstance().reloadConfigs();
        sender.sendMessage(Util.parsePrefixOnly(SPUMessage.CONFIG_RELOADED.getMessage()));
        return false;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
