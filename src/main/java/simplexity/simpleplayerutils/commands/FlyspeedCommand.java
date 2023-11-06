package simplexity.simpleplayerutils.commands;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import simplexity.simpleplayerutils.util.SPUMessage;
import simplexity.simpleplayerutils.util.SPUPerm;
import simplexity.simpleplayerutils.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlyspeedCommand implements TabExecutor {
    
    final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        float maxForMessage = config.getInt("max-flyspeed");
        float minForMessage = config.getInt("min-flyspeed");
        if (args.length <= 1 && !(sender instanceof Player)) {
            sender.sendRichMessage(SPUMessage.ERROR_ONLY_PLAYER.getMessage());
            return false;
        }
        
        if (args.length > 1) {
            Player player = PlayerUtilsInterface.checkAdminPerms(SPUPerm.FLYSPEED_OTHERS.getPerm(), sender, args);
            if (player == null) {
                return false;
            }
            if (args[1].equalsIgnoreCase("reset")) {
                resetPlayerFlySpeed(sender, player);
                return true;
            }
            if (args[1].equalsIgnoreCase("get")) {
                getPlayerFlySpeed(sender, player, player.getFlySpeed());
            }
            if (args[1].equalsIgnoreCase("set")) {
                float speed;
                try {
                    speed = Float.parseFloat(args[2]);
                } catch (NumberFormatException | NullPointerException e) {
                    sender.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                            String.valueOf(minForMessage), String.valueOf(maxForMessage)));
                    return false;
                }
                speed = speed / 10;
                setPlayerFlySpeed(sender, player, speed);
                return true;
            }
            getPlayerFlySpeed(sender, player, player.getFlySpeed());
            return true;
        }
        Player playerSender = ((Player) sender).getPlayer();
        if (playerSender == null) {
            sender.sendMessage(Util.parsePrefixOnly(SPUMessage.ERROR_GENERAL.getMessage()));
            return false;
        }
        if (args.length == 0) {
            getPlayerFlySpeed(null, playerSender, playerSender.getFlySpeed());
            return true;
        }
        
        if (args[0].equalsIgnoreCase("reset")) {
            resetPlayerFlySpeed(null, playerSender);
            return true;
        }
        
        if (args[0].equalsIgnoreCase("get")) {
            getPlayerFlySpeed(null, playerSender, playerSender.getFlySpeed());
            return true;
        }
        float speed;
        try {
            speed = Float.parseFloat(args[0]);
        } catch (NumberFormatException | NullPointerException e) {
            playerSender.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                    String.valueOf(minForMessage), String.valueOf(maxForMessage)));
            return false;
        }
        speed = speed / 10;
        setPlayerFlySpeed(null, playerSender, speed);
        return true;
    }
    
    private void setPlayerFlySpeed(CommandSender initiator, Player targetPlayer, float speed) {
        Component initiatorName;
        if (initiator instanceof Player) {
            initiatorName = ((Player) initiator).displayName();
        } else {
            initiatorName = miniMessage.deserialize(SPUMessage.CONSOLE_FORMAT.getMessage());
        }
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        String humanReadableSpeed = String.valueOf(speed * 10);
        float maxForMessage = config.getInt("max-flyspeed");
        float minForMessage = config.getInt("min-flyspeed");
        float maxSpeed = config.getInt("max-flyspeed");
        float minSpeed = config.getInt("min-flyspeed");
        maxSpeed = maxSpeed / 10;
        minSpeed = minSpeed / 10;
        boolean speedWithinBounds = (minSpeed <= speed) && (-1 <= speed) || (!(speed >= 1)) || (!(speed >= maxSpeed));
        if (initiator != null && speedWithinBounds) {
            targetPlayer.setFlySpeed(speed);
            initiator.sendMessage(Util.parseValueAndTarget(SPUMessage.FLYSPEED_SET_OTHER.getMessage(),
                    humanReadableSpeed, targetPlayer.displayName()));
            targetPlayer.sendMessage(Util.parseValueAndInitiator(SPUMessage.FLYSPEED_SET_BY_OTHER.getMessage(),
                    humanReadableSpeed, initiatorName));
            return;
        }
        if (initiator != null) {
            initiator.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                    String.valueOf(minForMessage), String.valueOf(maxForMessage)));
            return;
        }
        if (speedWithinBounds) {
            targetPlayer.setFlySpeed(speed);
            targetPlayer.sendMessage(Util.parseSingleValueOnly(SPUMessage.FLYSPEED_SET.getMessage(), humanReadableSpeed));
            return;
        }
        targetPlayer.sendMessage(Util.parseMinMax(SPUMessage.SPEED_NUMBER_ERROR.getMessage(),
                String.valueOf(minForMessage), String.valueOf(maxForMessage)));
    }
    
    private void getPlayerFlySpeed(CommandSender initiator, Player targetPlayer, float speed) {
        String humanReadableSpeed = String.valueOf(speed * 10);
        if (initiator != null) {
            initiator.sendMessage(Util.parseValueAndTarget(SPUMessage.OTHER_CURRENT_FLYSPEED.getMessage(),
                    humanReadableSpeed, targetPlayer.displayName()));
            return;
        }
        targetPlayer.sendMessage(Util.parseSingleValueOnly(SPUMessage.OWN_CURRENT_FLYSPEED.getMessage(),
                humanReadableSpeed));
    }
    
    private void resetPlayerFlySpeed(CommandSender initiator, Player targetPlayer) {
        Component initiatorName;
        if (initiator instanceof Player) {
            initiatorName = ((Player) initiator).displayName();
        } else {
            initiatorName = miniMessage.deserialize(SPUMessage.CONSOLE_FORMAT.getMessage());
        }
        if (initiator != null) {
            targetPlayer.setFlySpeed(0.1f);
            initiator.sendMessage(Util.parseTargetOnly(SPUMessage.FLYSPEED_RESET_OTHER.getMessage(),
                    targetPlayer.displayName()));
            targetPlayer.sendMessage(Util.parseInitiatorOnly(SPUMessage.FLYSPEED_RESET_BY_OTHER.getMessage(),
                    initiatorName));
            return;
        }
        targetPlayer.setFlySpeed(0.1f);
        targetPlayer.sendMessage(Util.parsePrefixOnly(SPUMessage.FLYSPEED_RESET.getMessage()));
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
