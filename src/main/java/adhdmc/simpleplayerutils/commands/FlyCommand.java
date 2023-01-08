package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUKey;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import net.kyori.adventure.text.Component;
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

public class FlyCommand implements CommandExecutor, TabCompleter {
    NamespacedKey flyStatus = SPUKey.FLY_STATUS.getKey();
    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //If console runs this without supplying a player, error and return
        if (args.length == 0 && !(sender instanceof Player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        //If the sender does not have permission to either set their own flight, or others, error and return
        if (!(sender.hasPermission(SPUPerm.FLY.getPerm()) || sender.hasPermission(SPUPerm.FLY_OTHERS.getPerm()))) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            return false;
        }
        //If the sender doesn't have perms to set their own flight, and supplies no other player, error and return
        if (args.length == 0 && !sender.hasPermission(SPUPerm.FLY.getPerm())) {
            sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            return false;
        }
        //Toggle flight
        if (args.length == 0) {
            Player player = (Player) sender;
            flyToggle(player, null, null);
            return true;
        }
        //Toggle another player's flight
        if (args.length == 1) {
            //Check if supplied argument is a player that exists, error and return if it is not
            Player player = SimplePlayerUtils.getInstance().getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_VALID_PLAYER_SUPPLIED.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                        Placeholder.parsed("name", args[0])));
                return false;
            }
            if (!sender.hasPermission(SPUPerm.FLY_OTHERS.getPerm())) {
                sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
                return false;
            }
            Component senderName;
            if (sender instanceof Player player1) {
                senderName = player1.displayName();
            } else {
                senderName = miniMessage.deserialize(SPUMessage.CONSOLE_FORMAT.getMessage());
            }
            flyToggle(player, sender, senderName);
            return true;
        }
        //If someone has made it this far, there's an error, send them an error and return.
        sender.sendMessage(miniMessage.deserialize(SPUMessage.ERROR_GENERAL.getMessage(),
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
        return false;
    }

    private void flyToggle(Player player, CommandSender sendingPlayer, Component sendingPlayerName) {
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        Byte flyState = playerPDC.get(flyStatus, PersistentDataType.BYTE);
        //If they have no set flystate, or it's off, set true, set flying, and send a message
        if (flyState == null || flyState == 0) {
            playerPDC.set(flyStatus, PersistentDataType.BYTE, (byte)1);
            player.setAllowFlight(true);
            player.setFlying(true);
            if (sendingPlayer == null) {
                player.sendMessage(miniMessage.deserialize(SPUMessage.FLY_ENABLED_SELF.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            } else {
                player.sendMessage(miniMessage.deserialize(SPUMessage.FLY_ENABLED_BY_OTHER.getMessage(),
                        Placeholder.component("sender", sendingPlayerName)));
                sendingPlayer.sendMessage(miniMessage.deserialize(SPUMessage.FLY_ENABLED_OTHER.getMessage(),
                        Placeholder.component("user", player.displayName())));
            }
            return;
        }
        //If their current flystate is on, set false, set flying false, send message
        if (flyState == 1) {
            playerPDC.set(flyStatus, PersistentDataType.BYTE, (byte)0);
            player.setAllowFlight(false);
            player.setFlying(false);
            if (sendingPlayer == null) {
                player.sendMessage(miniMessage.deserialize(SPUMessage.FLY_DISABLED_SELF.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
            } else {
                player.sendMessage(miniMessage.deserialize(SPUMessage.FLY_DISABLED_BY_OTHER.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                        Placeholder.component("sender", sendingPlayerName)));
                sendingPlayer.sendMessage(miniMessage.deserialize(SPUMessage.FLY_DISABLED_OTHER.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                        Placeholder.component("user", player.displayName())));
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
