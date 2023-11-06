package simplexity.simpleplayerutils.util;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SPUExpansion extends PlaceholderExpansion {
    
    @Override
    public @NotNull String getIdentifier() {
        return "spu";
    }
    
    @Override
    public @NotNull String getAuthor() {
        return SimplePlayerUtils.getInstance().getDescription().getAuthors().toString();
    }
    
    @Override
    public @NotNull String getVersion() {
        return SimplePlayerUtils.getInstance().getDescription().getVersion();
    }
    
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("afk")) {
            Player p = player.getPlayer();
            // TODO: Make Offline, AFK, and Online statuses configurable.
            if (p == null) {
                return "Offline";
            }
            if (p.isAfk()) {
                return "AFK";
            }
            if (!p.isAfk()) {
                return "";
            }
            return "";
        }
        return null;
    }
}
