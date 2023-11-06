package simplexity.simpleplayerutils.commands;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import simplexity.simpleplayerutils.util.SPUMessage;
import simplexity.simpleplayerutils.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public interface PlayerUtilsInterface {
    
    static Player checkAdminPerms(Permission runForOtherPerm, CommandSender sender, String[] args) {
        if (!sender.hasPermission(runForOtherPerm)) {
            sender.sendMessage(Util.parsePrefixOnly(SPUMessage.ERROR_TOO_MANY_ARGUMENTS.getMessage()));
            return null;
        }
        Player player = SimplePlayerUtils.getInstance().getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(Util.parsePlayerInput(SPUMessage.ERROR_NO_VALID_PLAYER_SUPPLIED.getMessage(), args[0]));
        }
        return player;
    }
    
}
