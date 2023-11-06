package simplexity.simpleplayerutils.util;

import simplexity.simpleplayerutils.SimplePlayerUtils;
import org.bukkit.NamespacedKey;

public enum SPUKey {
    FLY_STATUS(new NamespacedKey(SimplePlayerUtils.getInstance(), "fly-status")),
    AFK_SPAM_PREVENTION(new NamespacedKey(SimplePlayerUtils.getInstance(), "afk-spam-prevention"));
    
    final NamespacedKey key;
    
    SPUKey(NamespacedKey key) {
        this.key = key;
    }
    
    public NamespacedKey getKey() {
        return key;
    }
}
