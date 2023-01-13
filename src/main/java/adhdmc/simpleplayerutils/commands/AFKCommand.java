package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUKey;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AFKCommand implements CommandExecutor, TabCompleter {
    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    NamespacedKey spamPrevention = SPUKey.AFK_SPAM_PREVENTION.getKey();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //If console runs this without supplying a player, error and return
        if (args.length == 0 && !(sender instanceof Player playerSender)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        //If the sender does not have permission to either set their own afk, or others, error and return
        if (!(sender.hasPermission(SPUPerm.AFK.getPerm()) || sender.hasPermission(SPUPerm.AFK_OTHERS.getPerm()))) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            return false;
        }
        //If the sender doesn't have perms to set their own afk, and supplies no other player, error and return
        if (args.length == 0 && !sender.hasPermission(SPUPerm.AFK.getPerm())) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            return false;
        }
        if (args.length == 1) {
            //Check if supplied argument is a player that exists, error and return if it is not
            Player player = SimplePlayerUtils.getInstance().getServer().getPlayer(args[0]);
            if (!sender.hasPermission(SPUPerm.AFK_OTHERS.getPerm())) {
                sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
                return false;
            }
            if (player == null) {
                sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_VALID_PLAYER_SUPPLIED.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                        Placeholder.parsed("name", args[0])));
                return false;
            }
            player.setAfk(!player.isAfk());
            return true;
        }
        if (args.length == 0) {
            Player player = (Player) sender;
            PersistentDataContainer playerPDC = player.getPersistentDataContainer();
            if (playerPDC.getOrDefault(spamPrevention, PersistentDataType.BYTE, (byte)0) == (byte)1) {
                return false;
            }
            player.setAfk(!player.isAfk());
            return true;
        }
        return false;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
