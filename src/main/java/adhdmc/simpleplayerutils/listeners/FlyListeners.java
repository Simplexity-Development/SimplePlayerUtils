package adhdmc.simpleplayerutils.listeners;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUKey;
import adhdmc.simpleplayerutils.util.SPUMessage;
import adhdmc.simpleplayerutils.util.SPUPerm;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FlyListeners implements Listener {

    NamespacedKey flyStatus = SPUKey.FLY_STATUS.getKey();
    MiniMessage miniMessage = SimplePlayerUtils.getMiniMessage();
    byte off = (byte)0;
    byte on = (byte)1;
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent joinEvent){
        Player player = joinEvent.getPlayer();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        Bukkit.getScheduler().runTaskLater(SimplePlayerUtils.getInstance(), ()-> {
            byte playerFlyState = playerPDC.getOrDefault(flyStatus, PersistentDataType.BYTE, off);
            if (playerFlyState == on
                    && player.hasPermission(SPUPerm.FLY.getPerm())) {
                player.setAllowFlight(true);
                if (!player.isOnGround()) {
                    player.setFlying(true);
                }
                player.sendMessage(miniMessage.deserialize(SPUMessage.FLY_ENABLED_SELF.getMessage(),
                        Placeholder.parsed("plugin_prefix", SPUMessage.PLUGIN_PREFIX.getMessage())));
                return;
            }
            if (playerFlyState == on && !player.hasPermission(SPUPerm.FLY.getPerm())) {
                playerPDC.set(flyStatus, PersistentDataType.BYTE, off);
            }
        }, 4);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent worldEvent) {
        Player player = worldEvent.getPlayer();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        Byte playerFlyState = playerPDC.getOrDefault(flyStatus, PersistentDataType.BYTE, off);
        if (playerFlyState == on) {
            player.setAllowFlight(true);
            if (!player.isOnGround()) {
                player.setFlying(true);
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent respawnEvent) {
        Player player = respawnEvent.getPlayer();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        Byte playerFlyState = playerPDC.getOrDefault(flyStatus, PersistentDataType.BYTE, off);
        if (playerFlyState == on) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent gameModeChangeEvent) {
        Player player = gameModeChangeEvent.getPlayer();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        Bukkit.getScheduler().runTaskLater(SimplePlayerUtils.getInstance(), ()-> {
            Byte playerFlyState = playerPDC.getOrDefault(flyStatus, PersistentDataType.BYTE, off);
            if (playerFlyState == on) {
                player.setAllowFlight(true);
                if (!player.isOnGround()) {
                    player.setFlying(true);
                }
            }
        }, 1);
    }
}
