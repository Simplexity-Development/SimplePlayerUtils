package adhdmc.simpleplayerutils.util;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class Util {

    private static final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();

    public static Component parsePrefixOnly(String message) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()));
    }

    public static Component parseTargetOnly(String message, Component targetPlayer) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.component("target", targetPlayer));
    }

    public static Component parseInitiatorOnly(String message, Component initiatingPlayer) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.component("target", initiatingPlayer));
    }

    public static Component parseSingleValueOnly(String message, String value) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.parsed("value", value));
    }

    public static Component parseValueAndInitiator(String message, String value, Component initiatingPlayer) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.parsed("value", value),
                Placeholder.component("target", initiatingPlayer));
    }

    public static Component parseValueAndTarget(String message, String value, Component targetPlayer) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.parsed("value", value),
                Placeholder.component("target", targetPlayer));
    }

    public static Component parseMinMax(String message, String min, String max){
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.parsed("min", min),
                Placeholder.parsed("max", max));
    }

    public static Component parsePlayerInput(String message, String input) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.parsed("input", input));
    }

    public static Component parseItem(String message, String item) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.parsed("item", item));
    }
}
