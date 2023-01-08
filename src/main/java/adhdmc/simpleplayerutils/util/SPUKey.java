package adhdmc.simpleplayerutils.util;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import org.bukkit.NamespacedKey;

public enum SPUKey {
    FLY_STATUS(new NamespacedKey(SimplePlayerUtils.getInstance(), "fly-status"));

    final NamespacedKey key;

    SPUKey(NamespacedKey key) {
        this.key = key;
    }

    public NamespacedKey getKey() {
        return key;
    }
}
