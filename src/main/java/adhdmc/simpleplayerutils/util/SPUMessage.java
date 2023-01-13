package adhdmc.simpleplayerutils.util;

public enum SPUMessage {
    PLUGIN_PREFIX("<white>[<gradient:aqua:dark_aqua>SimplePlayerUtils<reset><white>]<reset>"),
    ENABLED("Enabled"),
    DISABLED("Disabled"),
    CONSOLE_FORMAT("<red>[<dark_red>Console</dark_red>]</red> "),
    //coords
    COORD_X("x,"),
    COORD_Y("y, "),
    COORD_Z("z"),
    //time
    YEAR("year"),
    YEARS("years"),
    MONTH("month"),
    MONTHS("months"),
    WEEK("week"),
    WEEKS("weeks"),
    DAY("day"),
    DAYS("days"),
    HOUR("hour"),
    HOURS("hours"),
    MINUTE("minute"),
    MINUTES("minutes"),
    SECOND("second"),
    SECONDS("seconds"),
    CONFIG_RELOADED("<plugin_prefix> <gold>Simple Player Utils Config has been reloaded"),
    //Errors
    ERROR_GENERAL("<plugin_prefix> <red>Sorry, that didn't seem to work. Please check that your sytax is correct, and that you have the proper permissions, and try again."),
    ERROR_NO_PERMISSION("<plugin_prefix> <red>Sorry but you do not have permission to use this command"),
    ERROR_ONLY_PLAYER("<red>Only a player may execute this command."),
    ERROR_NO_VALID_PLAYER_SUPPLIED("<plugin_prefix> <red>No valid player was supplied. <name> is not the name of a valid online player, please check your spelling and try again"),
    //Commands
    AFK_SERVER_OUTPUT("<gray><displayname> is now AFK"),
    AFK_RETURN_SERVER_OUTPUT("<gray><displayname> is no longer AFK"),
    AFK_USER_OUTPUT("<yellow>You are now AFK"),
    AFK_RETURN_USER_OUTPUT("<yellow>You are no longer AFK"),
    HAT_OUTPUT("<plugin_prefix> <green>Successfully put on a hat!"),
    HAT_ERROR_HAND_EMPTY("<plugin_prefix> <red>You cannot put air on your head"),
    HAT_ERROR_BINDING("<plugin_prefix> <red>Uh oh... Your helmet has curse of binding on it.. That's unfortunate. No hat for you."),
    ANVIL_COMMAND_OTHER("<plugin_prefix> <gray>Anvil opened for <user>"),
    CARTOGRAPHY_COMMAND_OTHER("<plugin_prefix> <gray>Cartography table opened for <user>"),
    CRAFT_COMMAND_OTHER("<plugin_prefix> <gray>Crafting table opened for <user>"),
    ENDERCHEST_COMMAND_OTHER("<plugin_prefix> <gray>Enderchest opened for <user>"),
    LOOM_COMMAND_OTHER("<plugin_prefix> <gray>Loom opened for <user>"),
    SMITHING_COMMAND_OTHER("<plugin_prefix> <gray>Smithing table opened for <user>"),
    STONECUTTER_COMMAND_OTHER("<plugin_prefix> <gray>Stonecutter opened for <user>"),
    GRINDSTONE_COMMAND_OTHER("<plugin_prefix> <gray>Grindstone opened for <user>"),
    RENAME_COMMAND_FEEDBACK("<plugin_prefix> <green>You have renamed your item from <oldname> to <newname>"),
    DYE_COMMAND_FEEDBACK("<plugin_prefix> <green>You have dyed your <item> <color><color_unparsed>"),
    DYE_ERROR_CANNOT_DYE("<plugin_prefix> <red>Sorry, <item> cannot be dyed, please hold a dyable item"),
    FLY_ENABLED_SELF("<plugin_prefix> <green>Your flight has been enabled"),
    FLY_DISABLED_SELF("<plugin_prefix> <gray>Your flight has been disabled"),
    FLY_ENABLED_OTHER("<plugin_prefix> <green>You have enabled flight for <user>"),
    FLY_DISABLED_OTHER("<plugin_prefix> <gray>You have disabled flight for <user>"),
    FLY_ENABLED_BY_OTHER("<plugin_prefix> <green><sender> has enabled flight for you"),
    FLY_DISABLED_BY_OTHER("<plugin_prefix> <gray><sender> has disabled your flight"),
    OTHER_CURRENT_WALKSPEED("<plugin_prefix> <user>'s current walkspeed is <value>"),
    OWN_CURRENT_WALKSPEED("<plugin_prefix> Your walkspeed is currently set to"),
    WALKSPEED_SET("<plugin_prefix> <green>Your walkspeed has been set to <value>"),
    WALKSPEED_RESET("<plugin_prefix> <green>Your walkspeed has been reset"),
    WALKSPEED_SET_OTHER("<plugin_prefix> <green>You set <user>'s walkspeed to <value>"),
    WALKSPEED_RESET_OTHER("<plugin_prefix> <green><user>'s walkspeed has been reset"),
    WALKSPEED_SET_BY_OTHER("<plugin_prefix> <green>Your walkspeed has been set to <value> by <sender>"),
    WALKSPEED_RESET_BY_OTHER("<plugin_prefix> <green>Your walkspeed has been reset by <sender>"),
    OTHER_CURRENT_FLYSPEED("<plugin_prefix> <user>'s current flyspeed is <value>"),
    OWN_CURRENT_FLYSPEED("<plugin_prefix> Your flyspeed is currently set to"),
    FLYSPEED_SET("<plugin_prefix> <green>Your flyspeed has been set to <value>"),
    FLYSPEED_RESET("<plugin_prefix> <green>Your flyspeed has been reset"),
    FLYSPEED_SET_OTHER("<plugin_prefix> <green>You set <user>'s flyspeed to <value>"),
    FLYSPEED_RESET_OTHER("<plugin_prefix> <green><user>'s flyspeed has been reset"),
    FLYSPEED_SET_BY_OTHER("<plugin_prefix> <green>Your flyspeed has been set to <value> by <sender>"),
    FLYSPEED_RESET_BY_OTHER("<plugin_prefix> <green>Your flyspeed has been reset by <sender>"),
    SPEED_NUMBER_ERROR("<plugin_prefix> <red>Please use a number between -10 and 10")

    ;
    String message;

    SPUMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
