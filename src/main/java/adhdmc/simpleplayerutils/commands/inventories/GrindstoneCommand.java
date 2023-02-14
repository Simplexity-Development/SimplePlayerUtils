package adhdmc.simpleplayerutils.commands.inventories;

import adhdmc.simpleplayerutils.commands.util.CommandOnOther;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.SPUSound;
import adhdmc.simpleplayerutils.util.Util;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GrindstoneCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player player = CommandOnOther.runCommandOnOtherPlayer(SPUPerm.GRINDSTONE_OTHER.getPerm(), sender, args);
            if (player == null) {
                return false;
            }
            openGrindstone(player, sender);
            return true;
        }
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        openGrindstone(player, null);
        return true;
    }

    private void openGrindstone(Player player, CommandSender sender){
        Location playerLocation = player.getLocation();
        player.openGrindstone(playerLocation, true);
        player.playSound(playerLocation, SPUSound.GRINDSTONE_SOUND.getSound(), 1, 1);
        if (sender != null) {
            sender.sendMessage(Util.parseTargetOnly(SPUMessage.GRINDSTONE_COMMAND_OTHER.getMessage(), player.displayName()));
        }

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
