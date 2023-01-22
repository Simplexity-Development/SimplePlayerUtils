package adhdmc.simpleplayerutils.config;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;

public class Defaults {

    private static final HashSet<Material> hatBlacklist = new HashSet<>();

    public static void setConfigDefaults(){
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        config.addDefault("min-flyspeed", -10);
        config.addDefault("min-walkspeed", -10);
        config.addDefault("max-flyspeed", 10);
        config.addDefault("max-walkspeed", 10);
        config.addDefault("chatting-disables-afk", true);
        config.addDefault("hat-blocked-materials", List.of("AIR","SPAWNER", "COMMAND_BLOCK", "STRUCTURE_VOID"));
        config.addDefault("list-is-whitelist", false);
        config.addDefault("rename-max-characters", 50);
        config.addDefault("craft-open-sound", "BLOCK_BAMBOO_WOOD_BREAK");
        config.addDefault("cartography-open-sound", "UI_CARTOGRAPHY_TABLE_TAKE_RESULT");
        config.addDefault("stonecutter-open-sound", "UI_STONECUTTER_TAKE_RESULT");
        config.addDefault("smithing-open-sound", "BLOCK_SMITHING_TABLE_USE");
        config.addDefault("grindstone-open-sound", "BLOCK_GRINDSTONE_USE");
        config.addDefault("loom-open-sound", "ENTITY_VILLAGER_WORK_SHEPHERD");
        config.addDefault("enderchest-open-sound", "BLOCK_ENDER_CHEST_OPEN");
    }

    public static void fillBlacklist() {
        hatBlacklist.clear();
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        List<String> configuredList = config.getStringList("hat-blocked-materials");
        for (String item : configuredList) {
            Material blockedMaterial;
            blockedMaterial = Material.matchMaterial(item);
            if (blockedMaterial == null) {
                SimplePlayerUtils.SPULogger().warning(item + " is not a valid material to blacklist. Please be sure you are using materials from https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
                continue;
            }
            hatBlacklist.add(blockedMaterial);
        }
    }

    public static HashSet<Material> getHatBlacklist() {
        return hatBlacklist;
    }
}
