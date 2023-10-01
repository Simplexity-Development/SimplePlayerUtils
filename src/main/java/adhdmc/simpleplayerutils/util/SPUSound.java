package adhdmc.simpleplayerutils.util;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public enum SPUSound {
    CRAFTING_SOUND(Sound.BLOCK_BAMBOO_WOOD_BREAK),
    ANVIL_SOUND(Sound.BLOCK_ANVIL_USE),
    CARTOGRAPHY_SOUND(Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT),
    STONECUTTER_SOUND(Sound.UI_STONECUTTER_TAKE_RESULT),
    SMITHING_SOUND(Sound.BLOCK_SMITHING_TABLE_USE),
    GRINDSTONE_SOUND(Sound.BLOCK_GRINDSTONE_USE),
    LOOM_SOUND(Sound.ENTITY_VILLAGER_WORK_SHEPHERD),
    ENDERCHEST_SOUND(Sound.BLOCK_ENDER_CHEST_OPEN),
    TRASH_SOUND(Sound.BLOCK_BARREL_OPEN),
    TRASH_ALERT(Sound.BLOCK_NOTE_BLOCK_PLING);
    Sound sound;
    
    SPUSound(Sound sound) {
        this.sound = sound;
    }
    
    public static void setConfiguredSounds() {
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        CRAFTING_SOUND.setSound(checkSound(config.getString("craft-open-sound")));
        ANVIL_SOUND.setSound(checkSound(config.getString("anvil-open-sound")));
        CARTOGRAPHY_SOUND.setSound(checkSound(config.getString("cartography-open-sound")));
        STONECUTTER_SOUND.setSound(checkSound(config.getString("stonecutter-open-sound")));
        SMITHING_SOUND.setSound(checkSound(config.getString("smithing-open-sound")));
        GRINDSTONE_SOUND.setSound(checkSound(config.getString("grindstone-open-sound")));
        LOOM_SOUND.setSound(checkSound(config.getString("loom-open-sound")));
        ENDERCHEST_SOUND.setSound(checkSound(config.getString("enderchest-open-sound")));
        TRASH_SOUND.setSound(checkSound(config.getString("trash-open-sound")));
        TRASH_ALERT.setSound(checkSound(config.getString("trash-alert")));
    }
    
    private static Sound checkSound(String string) {
        if (string == null) {
            return null;
        }
        if (string.equalsIgnoreCase("none")) {
            return null;
        }
        Sound soundToCheck;
        try {
            soundToCheck = Sound.valueOf(string);
        } catch (ClassCastException e) {
            SimplePlayerUtils.SPULogger().warning("Sound " + string + " unable to be cast to sound, please be sure you are using a sound from https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html, or write \"none\" for no sound. Setting sound to null.");
            return null;
        }
        return soundToCheck;
    }
    
    public Sound getSound() {
        return sound;
    }
    
    private void setSound(Sound sound) {
        this.sound = sound;
    }
}
