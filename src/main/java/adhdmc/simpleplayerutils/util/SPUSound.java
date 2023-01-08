package adhdmc.simpleplayerutils.util;

import org.bukkit.Sound;

public enum SPUSound {
    CRAFTING_SOUND(Sound.BLOCK_BAMBOO_WOOD_BREAK),
    ANVIL_SOUND(Sound.BLOCK_ANVIL_USE),
    CARTOGRAPHY_SOUND(Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT),
    STONECUTTER_SOUND(Sound.UI_STONECUTTER_TAKE_RESULT),
    SMITHING_SOUND(Sound.BLOCK_SMITHING_TABLE_USE),
    GRINDSTONE_SOUND(Sound.BLOCK_GRINDSTONE_USE),
    LOOM_SOUND(Sound.ENTITY_VILLAGER_WORK_SHEPHERD);
    final Sound sound;

    SPUSound(Sound sound) {
        this.sound = sound;
    }
    public Sound getSound(){
        return sound;
    }
}
