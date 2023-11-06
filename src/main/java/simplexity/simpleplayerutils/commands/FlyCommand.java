package simplexity.simpleplayerutils.commands;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import simplexity.simpleplayerutils.util.SPUKey;
import simplexity.simpleplayerutils.util.SPUMessage;
import simplexity.simpleplayerutils.util.SPUPerm;
import simplexity.simpleplayerutils.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlyCommand implements TabExecutor {
    
    final NamespacedKey flyStatus = SPUKey.FLY_STATUS.getKey();
    final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player player = PlayerUtilsInterface.checkAdminPerms(SPUPerm.FLY_OTHERS.getPerm(), sender, args);
            if (player == null) {
                return false;
            }
            Component senderName;
            if (sender instanceof Player initiatingPlayer) {
                senderName = initiatingPlayer.displayName();
            } else {
                senderName = miniMessage.deserialize(SPUMessage.CONSOLE_FORMAT.getMessage());
            }
            flyToggle(player, sender, senderName);
            return true;
        }
        //If console runs this without supplying a player, error and return
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        flyToggle(player, null, null);
        return true;
    }
    
    private void flyToggle(Player player, CommandSender initiatingPlayer, Component initiatingPlayerName) {
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        Byte flyState = playerPDC.get(flyStatus, PersistentDataType.BYTE);
        //If they have no set flystate, or it's off, set true, set flying, and send a message
        if (flyState == null || flyState == 0) {
            playerPDC.set(flyStatus, PersistentDataType.BYTE, (byte) 1);
            player.setAllowFlight(true);
            player.setFlying(true);
            if (initiatingPlayer == null) {
                player.sendMessage(Util.parsePrefixOnly(SPUMessage.FLY_ENABLED_SELF.getMessage()));
            } else {
                player.sendMessage(Util.parseInitiatorOnly(SPUMessage.FLY_ENABLED_BY_OTHER.getMessage(), initiatingPlayerName));
                initiatingPlayer.sendMessage(Util.parseTargetOnly(SPUMessage.FLY_ENABLED_OTHER.getMessage(), player.displayName()));
            }
            return;
        }
        //If their current flystate is on, set false, set flying false, send message
        if (flyState == 1) {
            playerPDC.set(flyStatus, PersistentDataType.BYTE, (byte) 0);
            player.setAllowFlight(false);
            player.setFlying(false);
            if (initiatingPlayer == null) {
                player.sendMessage(Util.parsePrefixOnly(SPUMessage.FLY_DISABLED_SELF.getMessage()));
            } else {
                player.sendMessage(Util.parseInitiatorOnly(SPUMessage.FLY_DISABLED_BY_OTHER.getMessage(), initiatingPlayerName));
                initiatingPlayer.sendMessage(Util.parseTargetOnly(SPUMessage.FLY_DISABLED_OTHER.getMessage(), player.displayName()));
            }
        }
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
