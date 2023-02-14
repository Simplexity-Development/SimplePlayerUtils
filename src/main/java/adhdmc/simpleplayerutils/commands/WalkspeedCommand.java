package adhdmc.simpleplayerutils.commands;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.commands.util.CommandOnOther;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import adhdmc.simpleplayerutils.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WalkspeedCommand implements TabExecutor {
    final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        float maxForMessage = config.getInt("max-walkspeed");
        float minForMessage = config.getInt("min-walkspeed");
        if (args.length <= 1 && !(sender instanceof Player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }

        if (args.length > 1) {
            Player player = CommandOnOther.runCommandOnOtherPlayer(SPUPerm.WALKSPEED_OTHERS.getPerm(), sender, args);
            if (player == null) {
                return false;
            }
            if (args[1].equalsIgnoreCase("reset")) {
                resetPlayerWalkSpeed(sender, player);
                return true;
            }
            if (args[1].equalsIgnoreCase("get")) {
                getPlayerWalkSpeed(sender, player, player.getWalkSpeed());
            }
            if (args[1].equalsIgnoreCase("set")) {
                float speed;
                try {
                    speed = Float.parseFloat(args[2]);
                } catch (NumberFormatException|NullPointerException e) {
                    sender.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                            String.valueOf(minForMessage), String.valueOf(maxForMessage)));
                    return false;
                }
                speed = speed/10;
                setPlayerWalkSpeed(sender, player, speed);
                return true;
            }
            getPlayerWalkSpeed(sender, player, player.getWalkSpeed());
            return true;
        }
        Player playerSender = ((Player) sender).getPlayer();
        if (playerSender == null) {
            sender.sendMessage(Util.parsePrefixOnly(SPUMessage.ERROR_GENERAL.getMessage()));
            return false;
        }
        if (args.length == 0) {
            getPlayerWalkSpeed(null, playerSender, playerSender.getWalkSpeed());
            return true;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            resetPlayerWalkSpeed(null, playerSender);
            return true;
        }

        if (args[0].equalsIgnoreCase("get")) {
            getPlayerWalkSpeed(null, playerSender, playerSender.getWalkSpeed());
            return true;
        }
        float speed;
        try {
            speed = Float.parseFloat(args[0]);
        } catch (NumberFormatException|NullPointerException e) {
            playerSender.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                    String.valueOf(minForMessage), String.valueOf(maxForMessage)));
            return false;
        }
        speed = speed/10;
        setPlayerWalkSpeed(null, playerSender, speed);
        return true;
    }

    private void setPlayerWalkSpeed(CommandSender initiator, Player targetPlayer, float speed) {
        Component initiatorName;
        if (initiator instanceof Player) {
            initiatorName = ((Player) initiator).displayName();
        } else {
            initiatorName = miniMessage.deserialize(SPUMessage.CONSOLE_FORMAT.getMessage());
        }
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        String humanReadableSpeed = String.valueOf(speed * 10);
        float maxForMessage = config.getInt("max-walkspeed");
        float minForMessage = config.getInt("min-walkspeed");
        float maxSpeed = config.getInt("max-walkspeed");
        float minSpeed = config.getInt("min-walkspeed");
        maxSpeed = maxSpeed / 10;
        minSpeed = minSpeed / 10;
        boolean speedWithinBounds = (minSpeed <= speed) && (-1 <= speed) || (!(speed >= 1)) || (!(speed >= maxSpeed));
        if (initiator != null && speedWithinBounds) {
            targetPlayer.setWalkSpeed(speed);
            initiator.sendMessage(Util.parseValueAndTarget(SPUMessage.WALKSPEED_SET_OTHER.getMessage(),
                    humanReadableSpeed, targetPlayer.displayName()));
            targetPlayer.sendMessage(Util.parseValueAndInitiator(SPUMessage.WALKSPEED_SET_BY_OTHER.getMessage(),
                    humanReadableSpeed, initiatorName));
            return;
        }
        if (initiator != null) {
            initiator.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                    String.valueOf(minForMessage), String.valueOf(maxForMessage)));
            return;
        }
        if (speedWithinBounds) {
            targetPlayer.setWalkSpeed(speed);
            targetPlayer.sendMessage(Util.parseSingleValueOnly(SPUMessage.WALKSPEED_SET.getMessage(), humanReadableSpeed));
            return;
        }
        targetPlayer.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                String.valueOf(minForMessage), String.valueOf(maxForMessage)));
    }

    private void getPlayerWalkSpeed(CommandSender initiator, Player targetPlayer, float speed) {
        String humanReadableSpeed = String.valueOf(speed * 10);
        if (initiator != null) {
            initiator.sendMessage(Util.parseValueAndTarget(SPUMessage.OTHER_CURRENT_WALKSPEED.getMessage(),
                    humanReadableSpeed, targetPlayer.displayName()));
            return;
        }
        targetPlayer.sendMessage(Util.parseSingleValueOnly(SPUMessage.OWN_CURRENT_WALKSPEED.getMessage(),
                humanReadableSpeed));
    }

    private void resetPlayerWalkSpeed(CommandSender initiator, Player targetPlayer) {
        Component initiatorName;
        if (initiator instanceof Player) {
            initiatorName = ((Player) initiator).displayName();
        } else {
            initiatorName = miniMessage.deserialize(SPUMessage.CONSOLE_FORMAT.getMessage());
        }
        if (initiator != null) {
            targetPlayer.setWalkSpeed(0.2f);
            initiator.sendMessage(Util.parseTargetOnly(SPUMessage.WALKSPEED_RESET_OTHER.getMessage(),
                    targetPlayer.displayName()));
            targetPlayer.sendMessage(Util.parseInitiatorOnly(SPUMessage.WALKSPEED_RESET_BY_OTHER.getMessage(),
                    initiatorName));
            return;
        }
        targetPlayer.setWalkSpeed(0.2f);
        targetPlayer.sendMessage(Util.parsePrefixOnly(SPUMessage.WALKSPEED_RESET.getMessage()));
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
