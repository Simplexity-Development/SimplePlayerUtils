package adhdmc.simpleplayerutils.listeners;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerChat(AsyncChatEvent chatEvent) {
        if (chatEvent.getPlayer().isAfk() &&
                SimplePlayerUtils.getInstance().getConfig().getBoolean("chatting-disables-afk")) {
            chatEvent.getPlayer().setAfk(false);
        }
    }
}
