package adhdmc.simpleplayerutils.config;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;

public class Defaults {
    
    private static final HashSet<Material> hatBlacklist = new HashSet<>();
    private static final HashSet<Material> trashBlacklist = new HashSet<>();
    
    public static void setConfigDefaults() {
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        config.addDefault("min-flyspeed", -10);
        config.addDefault("min-walkspeed", -10);
        config.addDefault("max-flyspeed", 10);
        config.addDefault("max-walkspeed", 10);
        config.addDefault("chatting-disables-afk", true);
        config.addDefault("hat-respects-binding-enchant", true);
        config.addDefault("hat-blocked-materials", List.of("AIR", "SPAWNER", "COMMAND_BLOCK", "STRUCTURE_VOID"));
        config.addDefault("list-is-whitelist", false);
        config.addDefault("rename-max-characters", 50);
        config.addDefault("trash-blacklist", List.of("DIAMOND", "DIAMOND_BLOCK", "DIAMOND_ORE",
                "DEEPSLATE_DIAMOND_ORE", "DIAMOND_HELMET", "DIAMOND_CHESTPLATE", "DIAMOND_LEGGINGS",
                "DIAMOND_BOOTS", "DIAMOND_PICKAXE", "DIAMOND_HOE", "DIAMOND_SHOVEL", "DIAMOND_AXE",
                "DIAMOND_SWORD", "NETHERITE_INGOT", "ANCIENT_DEBRIS", "NETHERITE_SCRAP", "NETHERITE_HELMET",
                "NETHERITE_CHESTPLATE", "NETHERITE_LEGGINGS", "NETHERITE_BOOTS", "NETHERITE_PICKAXE",
                "NETHERITE_HOE", "NETHERITE_SHOVEL", "NETHERITE_AXE", "NETHERITE_SWORD", "NETHERITE_BLOCK",
                "BEACON", "NETHER_STAR", "SHULKER_BOX", "WHITE_SHULKER_BOX", "LIGHT_GRAY_SHULKER_BOX",
                "GRAY_SHULKER_BOX", "BLACK_SHULKER_BOX", "BROWN_SHULKER_BOX", "PINK_SHULKER_BOX", "MAGENTA_SHULKER_BOX",
                "PURPLE_SHULKER_BOX", "BLUE_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX", "CYAN_SHULKER_BOX",
                "LIME_SHULKER_BOX", "GREEN_SHULKER_BOX", "YELLOW_SHULKER_BOX", "ORANGE_SHULKER_BOX", "RED_SHULKER_BOX"));
        config.addDefault("craft-open-sound", "BLOCK_BAMBOO_WOOD_BREAK");
        config.addDefault("cartography-open-sound", "UI_CARTOGRAPHY_TABLE_TAKE_RESULT");
        config.addDefault("stonecutter-open-sound", "UI_STONECUTTER_TAKE_RESULT");
        config.addDefault("smithing-open-sound", "BLOCK_SMITHING_TABLE_USE");
        config.addDefault("grindstone-open-sound", "BLOCK_GRINDSTONE_USE");
        config.addDefault("loom-open-sound", "ENTITY_VILLAGER_WORK_SHEPHERD");
        config.addDefault("enderchest-open-sound", "BLOCK_ENDER_CHEST_OPEN");
        config.addDefault("trash-open-sound", "BLOCK_BARREL_OPEN");
        config.addDefault("trash-alert", "BLOCK_NOTE_BLOCK_PLING");
    }
    
    public static void fillBlacklists() {
        hatBlacklist.clear();
        trashBlacklist.clear();
        FileConfiguration config = SimplePlayerUtils.getInstance().getConfig();
        List<String> configuredHatList = config.getStringList("hat-blocked-materials");
        List<String> configuredTrashList = config.getStringList("trash-blacklist");
        for (String item : configuredHatList) {
            Material blockedMaterial;
            blockedMaterial = Material.matchMaterial(item);
            if (blockedMaterial == null) {
                SimplePlayerUtils.SPULogger().warning(item + " is not a valid material to blacklist (/hat blacklist). Please be sure you are using materials from https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
                continue;
            }
            hatBlacklist.add(blockedMaterial);
        }
        for (String item : configuredTrashList) {
            Material blacklistedMaterial;
            blacklistedMaterial = Material.matchMaterial(item);
            if (blacklistedMaterial == null) {
                SimplePlayerUtils.SPULogger().warning(item + " is not a valid material to blacklist (/trash blacklist). Please be sure you are using materials from https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
                continue;
            }
            trashBlacklist.add(blacklistedMaterial);
        }
    }
    
    public static HashSet<Material> getHatBlacklist() {
        return hatBlacklist;
    }
    
    public static HashSet<Material> getTrashBlacklist() {
        return trashBlacklist;
    }
}
