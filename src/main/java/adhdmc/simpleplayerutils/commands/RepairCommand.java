package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RepairCommand implements TabExecutor {
    
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String argument;
        if (args.length == 1) {
            argument = args[0];
        } else {
            argument = args[1];
        }
        if (argument.equalsIgnoreCase("held")) {
            repairHeld(commandSender, args);
        }
        return false;
    }
    
    private void repairHeld(CommandSender sender, String[] args) {
        Player player = null;
        if (args.length > 1) {
            player = AbstractInventoryCommand.checkAdminPerms(SPUPerm.REPAIR_HELD_OTHER.getPerm(), sender, args);
            if (player == null) {
                return;
            }
        } else if (args.length == 1 && sender instanceof Player playerSender) {
            player = playerSender;
        } else if (args.length == 1) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
        }
        assert player != null;
        player.getInventory().getItemInMainHand().repair();
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
