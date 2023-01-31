package adhdmc.simpleplayerutils.util;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

import javax.annotation.Nullable;

public class Util {

    private static final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();

    public static Component messageParsing(String message, @Nullable Component targetPlayer, @Nullable Component initiatingPlayer, @Nullable Double value, @Nullable Float min, @Nullable Float max, @Nullable String item, @Nullable String input) {
        if (targetPlayer == null) {
            targetPlayer = Component.empty();
        }
        if (initiatingPlayer == null) {
            initiatingPlayer = Component.empty();
        }
        if (value == null) {
            value = (double) 0;
        }
        if (min == null) {
            min = 0f;
        }
        if (max == null) {
            max = 0f;
        }
        if (item == null) {
            item = "";
        }
        if (input == null) {
            input = "";
        }
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.component("target", targetPlayer),
                Placeholder.component("initiator", initiatingPlayer),
                Placeholder.parsed("value", String.valueOf(value)),
                Placeholder.parsed("min", String.valueOf(min)),
                Placeholder.parsed("max", String.valueOf(max)),
                Placeholder.parsed("item", item),
                Placeholder.parsed("input", input));
    }
}
