package adhdmc.simpleplayerutils.util;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class MessageParse {

    private static final MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();

    public static Component singlePlayerName(String message, Component targetPlayer, Component initiatingPlayer, int value, int min, int max, String item) {
        return miniMessage.deserialize(message,
                Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.component("target", targetPlayer),
                Placeholder.component("initiator", initiatingPlayer),
                Placeholder.parsed("value", String.valueOf(value)),
                Placeholder.parsed("min", String.valueOf(min)),
                Placeholder.parsed("max", String.valueOf(max)),
                Placeholder.parsed("item", item));
    }
}
