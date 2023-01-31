package adhdmc.simpleplayerutils.commands.inventories;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.SPUSound;
import adhdmc.simpleplayerutils.util.Util;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LoomCommand implements CommandExecutor, TabCompleter {
    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //If console runs this without supplying a player, error and return
        if (args.length == 0 && !(sender instanceof Player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        //If the sender does not have permission to either open an LOOM menu for themselves, or others, error and return
        if (!(sender.hasPermission(SPUPerm.LOOM.getPerm()) || sender.hasPermission(SPUPerm.LOOM_OTHER.getPerm()))) {
            sender.sendMessage(Util.messageParsing(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    null, null, null, null, null,null,null));
            return false;
        }
        //If the sender doesn't have perms to open an LOOM menu for themselves, and supplies no other player, error and return
        if (args.length == 0 && !sender.hasPermission(SPUPerm.LOOM.getPerm())) {
            sender.sendMessage(Util.messageParsing(SPUMessage.ERROR_NO_PERMISSION.getMessage(),
                    null, null, null, null, null,null,null));
            return false;
        }
        //If sender has permission and no other player is provided, open menu and return
        if (args.length == 0) {
            Player player = (Player) sender;
            openLoom(player, null);
            return true;
        }
        //If sender has permission and supplies an argument, check for player
        if (args.length == 1 && sender.hasPermission(SPUPerm.LOOM_OTHER.getPerm())) {
            Player player = SimplePlayerUtils.getInstance().getServer().getPlayer(args[0]);
            //If player doesn't exist, error and return
            if (player == null) {
                sender.sendMessage(Util.messageParsing(SPUMessage.ERROR_NO_VALID_PLAYER_SUPPLIED.getMessage(),
                        miniMessage.deserialize(args[0]), null, null, null, null,null,null));
                return false;
            }
            //if Player exists, open their LOOM menu and return
            openLoom(player, sender);
            return true;
        }
        //If someone has made it this far, there's an error, send them an error and return.
        sender.sendMessage(Util.messageParsing(SPUMessage.ERROR_GENERAL.getMessage(),
                null, null, null, null, null,null,null));
        return false;
    }

    private void openLoom(Player player, CommandSender sender){
        Location playerLocation = player.getLocation();
        player.openLoom(playerLocation, true);
        player.playSound(playerLocation, SPUSound.LOOM_SOUND.getSound(), 1, 1);
        if (sender != null) {
            sender.sendMessage(Util.messageParsing(SPUMessage.LOOM_COMMAND_OTHER.getMessage(),
                    player.displayName(), null, null, null, null,null,null));
        }

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
