package adhdmc.simpleplayerutils.listeners;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUKey;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.purpurmc.purpur.event.PlayerAFKEvent;

public class AFKListener implements Listener {
    final NamespacedKey spamPrevention = SPUKey.AFK_SPAM_PREVENTION.getKey();

    @EventHandler
    public void onAFKChange(PlayerAFKEvent AFKEvent){
        Player player = AFKEvent.getPlayer();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        //Hopefully, this will mean, if they're going afk it'll set it to 1, and then 3 ticks later, to 0
        if (!AFKEvent.isGoingAfk()) {
            playerPDC.set(spamPrevention, PersistentDataType.BYTE, (byte)1);
            Bukkit.getScheduler().runTaskLater(SimplePlayerUtils.getInstance(), ()-> {
                playerPDC.set(spamPrevention, PersistentDataType.BYTE, (byte)0);
            },3);
        }
    }
}
