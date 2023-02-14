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

public class SmithingCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player player = CommandOnOther.runCommandOnOtherPlayer(SPUPerm.SMITHING_OTHER.getPerm(), sender, args);
            if (player == null) {
                return false;
            }
            openSmithing(player, sender);
            return true;
        }
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        openSmithing(player, null);
        return true;
    }

    private void openSmithing(Player player, CommandSender sender){
        Location playerLocation = player.getLocation();
        player.openSmithingTable(playerLocation, true);
        player.playSound(playerLocation, SPUSound.SMITHING_SOUND.getSound(), 1, 1);
        if (sender != null) {
            sender.sendMessage(Util.parseTargetOnly(SPUMessage.SMITHING_COMMAND_OTHER.getMessage(), player.displayName()));
        }

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
