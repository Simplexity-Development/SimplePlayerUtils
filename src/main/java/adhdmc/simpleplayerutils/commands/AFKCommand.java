package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.commands.util.CommandOnOther;
import adhdmc.simpleplayerutils.util.SPUKey;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import org.bukkit.NamespacedKey;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AFKCommand implements TabExecutor {
    NamespacedKey spamPrevention = SPUKey.AFK_SPAM_PREVENTION.getKey();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player player = CommandOnOther.runCommandOnOtherPlayer(SPUPerm.AFK_OTHERS.getPerm(), sender, args);
            if (player == null) {
                return false;
            }
            toggleAFK(player);
        }
        if (!(sender instanceof Player playerSender)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        toggleAFK(playerSender);
        return true;
    }

    private void toggleAFK(Player player) {
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        if (playerPDC.getOrDefault(spamPrevention, PersistentDataType.BYTE, (byte)0) == (byte)1) {
            return;
        }
        player.setAfk(!player.isAfk());
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
