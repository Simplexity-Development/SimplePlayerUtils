package simplexity.simpleplayerutils.util;

public enum SPUMessage {
    PLUGIN_PREFIX("<white>[<gradient:aqua:dark_aqua>SimplePlayerUtils<reset><white>]<reset>"),
    CONSOLE_FORMAT("<red>[<dark_red>Console</dark_red>]</red> "),
    CONFIG_RELOADED("<plugin_prefix> <gold>Simple Player Utils Config has been reloaded"),
    //Errors
    ERROR_GENERAL("<plugin_prefix> <red>Sorry, that didn't seem to work. Please check that your syntax is correct, and that you have the proper permissions, and try again."),
    ERROR_TOO_MANY_ARGUMENTS("<plugin_prefix> <red>Sorry, you provided too many arguments. Please check your syntax and try again"),
    ERROR_ONLY_PLAYER("<red>Only a player may execute this command."),
    ERROR_NO_VALID_PLAYER_SUPPLIED("<plugin_prefix> <red>No valid player was supplied. <input> is not the name of a valid online player, please check your spelling and try again"),
    ERROR_NO_PERMISSION("<red>You do not have permission to run this command"),
    //Commands
    HAT_OUTPUT("<plugin_prefix> <green>Successfully put on a hat!"),
    HAT_ERROR_BLOCKED_ITEM("<plugin_prefix> <red>You cannot put <item> on your head"),
    HAT_ERROR_BINDING("<plugin_prefix> <red>Uh oh... Your helmet has curse of binding on it.. That's unfortunate. No hat for you."),
    ANVIL_COMMAND_OTHER("<plugin_prefix> <gray>Anvil opened for <target>"),
    CARTOGRAPHY_COMMAND_OTHER("<plugin_prefix> <gray>Cartography table opened for <target>"),
    CRAFT_COMMAND_OTHER("<plugin_prefix> <gray>Crafting table opened for <target>"),
    ENDERCHEST_COMMAND_OTHER("<plugin_prefix> <gray>Enderchest opened for <target>"),
    LOOM_COMMAND_OTHER("<plugin_prefix> <gray>Loom opened for <target>"),
    SMITHING_COMMAND_OTHER("<plugin_prefix> <gray>Smithing table opened for <target>"),
    STONECUTTER_COMMAND_OTHER("<plugin_prefix> <gray>Stonecutter opened for <target>"),
    GRINDSTONE_COMMAND_OTHER("<plugin_prefix> <gray>Grindstone opened for <target>"),
    TRASH_INVENTORY_NAME("<red>Trash bin"),
    TRASH_COMMAND_OTHER("<plugin_prefix> <gray>Trash bin opened for <target>"),
    TRASH_COMMAND_FEEDBACK("<plugin_prefix> <gray>Your trashed items have been deleted"),
    TRASH_BLACKLIST_ITEMS_DROPPED("<plugin_prefix> <gold>Hey! You had some items in the trash that are not allowed to be trashed, they've been dropped back on the ground"),
    RENAME_COMMAND_FEEDBACK("<plugin_prefix> <green>You have renamed your item to <input>"),
    RENAME_ERROR_INPUT_TOO_LONG("<plugin_prefix> <red>Sorry, the limit for the /rename command is <value> characters, excluding formatting and color tags."),
    FLY_ENABLED_SELF("<plugin_prefix> <green>Your flight has been enabled"),
    FLY_DISABLED_SELF("<plugin_prefix> <gray>Your flight has been disabled"),
    FLY_ENABLED_OTHER("<plugin_prefix> <green>You have enabled flight for <target>"),
    FLY_DISABLED_OTHER("<plugin_prefix> <gray>You have disabled flight for <target>"),
    FLY_ENABLED_BY_OTHER("<plugin_prefix> <green><initiator> has enabled flight for you"),
    FLY_DISABLED_BY_OTHER("<plugin_prefix> <gray><initiator> has disabled your flight"),
    OTHER_CURRENT_WALKSPEED("<plugin_prefix> <target>'s current walkspeed is <value>"),
    OWN_CURRENT_WALKSPEED("<plugin_prefix> Your walkspeed is currently set to <value>"),
    WALKSPEED_SET("<plugin_prefix> <green>Your walkspeed has been set to <value>"),
    WALKSPEED_RESET("<plugin_prefix> <green>Your walkspeed has been reset"),
    WALKSPEED_SET_OTHER("<plugin_prefix> <green>You set <target>'s walkspeed to <value>"),
    WALKSPEED_RESET_OTHER("<plugin_prefix> <green><target>'s walkspeed has been reset"),
    WALKSPEED_SET_BY_OTHER("<plugin_prefix> <green>Your walkspeed has been set to <value> by <initiator>"),
    WALKSPEED_RESET_BY_OTHER("<plugin_prefix> <green>Your walkspeed has been reset by <initiator>"),
    OTHER_CURRENT_FLYSPEED("<plugin_prefix> <target>'s current flyspeed is <value>"),
    OWN_CURRENT_FLYSPEED("<plugin_prefix> Your flyspeed is currently set to <value>"),
    FLYSPEED_SET("<plugin_prefix> <green>Your flyspeed has been set to <value>"),
    FLYSPEED_RESET("<plugin_prefix> <green>Your flyspeed has been reset"),
    FLYSPEED_SET_OTHER("<plugin_prefix> <green>You set <target>'s flyspeed to <value>"),
    FLYSPEED_RESET_OTHER("<plugin_prefix> <green><target>'s flyspeed has been reset"),
    FLYSPEED_SET_BY_OTHER("<plugin_prefix> <green>Your flyspeed has been set to <value> by <initiator>"),
    FLYSPEED_RESET_BY_OTHER("<plugin_prefix> <green>Your flyspeed has been reset by <initiator>"),
    SPEED_NUMBER_ERROR("<plugin_prefix> <red>Please use a number between <min> and <max>"),
    LOGGER_INVALID_LOCALE_KEY("The following locale key is not valid: ");
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
