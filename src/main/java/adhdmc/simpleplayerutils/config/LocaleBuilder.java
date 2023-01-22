package adhdmc.simpleplayerutils.config;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LocaleBuilder {

        private static LocaleBuilder instance;
        private final SimplePlayerUtils pluginInstance;
        private final String localeName = "locale.yml";
        private YamlConfiguration localeConfig = null;
        private File localeFile = null;



        private LocaleBuilder() {
            this.pluginInstance = (SimplePlayerUtils) SimplePlayerUtils.getInstance();
        }

        public static LocaleBuilder getInstance() {
            if (instance == null) {
                instance = new LocaleBuilder();
                instance.getLocaleConfig();
                instance.setDefaults();
                instance.saveConfig();
                instance.loadLocaleMessages();
            }
            return instance;
        }

        public void reloadConfig() {
            if (this.localeFile == null) {
                this.localeFile = new File(this.pluginInstance.getDataFolder(), localeName);
            }
            this.localeConfig = YamlConfiguration.loadConfiguration(this.localeFile);
            this.localeConfig.options().copyDefaults(true);
            InputStream defaultStream = this.pluginInstance.getResource(localeName);
            if (defaultStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
                this.localeConfig.setDefaults(defaultConfig);
            }
        }

        public YamlConfiguration getLocaleConfig() {
            if (this.localeConfig == null) {
                reloadConfig();
            }
            return this.localeConfig;
        }

        public void saveConfig() {
            getLocaleConfig();
            if (this.localeConfig == null || this.localeFile == null) {
                return;
            }
            try {
                this.getLocaleConfig().save(this.localeFile);
            } catch (IOException e) {
                pluginInstance.getLogger().severe("[saveConfig()] Could not save config to " + this.localeFile);
                e.printStackTrace();
            }
            if (!this.localeFile.exists()) {
                this.pluginInstance.saveResource(localeName, false);
            }
        }

        public void loadLocaleMessages() {
            FileConfiguration locale = getLocaleConfig();
            for (SPUMessage m : SPUMessage.values()) {
                String localeOption = m.toString().toLowerCase(java.util.Locale.ENGLISH).replace('_', '-');
                String message = locale.getString(localeOption, null);
                if (message == null) {
                    continue;
                }
                m.setMessage(message);
            }
        }

        public void setDefaults() {
            FileConfiguration config = getLocaleConfig();
            config.addDefault("plugin-prefix","<white>[<gradient:aqua:dark_aqua>SimplePlayerUtils<reset><white>]<reset>");
            config.addDefault("enabled","Enabled");
            config.addDefault("disabled","Disabled");
            config.addDefault("console-format","<red>[<dark_red>Console</dark_red>]</red> ");
            config.addDefault("coord-x","x,");
            config.addDefault("coord-y","y, ");
            config.addDefault("coord-z","z");
            config.addDefault("year","year");
            config.addDefault("years","years");
            config.addDefault("month","month");
            config.addDefault("months","months");
            config.addDefault("week","week");
            config.addDefault("weeks","weeks");
            config.addDefault("day","day");
            config.addDefault("days","days");
            config.addDefault("hour","hour");
            config.addDefault("hours","hours");
            config.addDefault("minute","minute");
            config.addDefault("minutes","minutes");
            config.addDefault("second","second");
            config.addDefault("seconds","seconds");
            config.addDefault("config-reloaded","<plugin_prefix> <gold>Simple Player Utils Config has been reloaded");
            config.addDefault("error-general","<plugin_prefix> <red>Sorry, that didn't seem to work. Please check that your sytax is correct, and that you have the proper permissions, and try again.");
            config.addDefault("error-no-permission","<plugin_prefix> <red>Sorry but you do not have permission to use this command");
            config.addDefault("error-only-player","<red>Only a player may execute this command.");
            config.addDefault("error-no-valid-player-supplied","<plugin_prefix> <red>No valid player was supplied. <name> is not the name of a valid online player, please check your spelling and try again");
            config.addDefault("hat-output","<plugin_prefix> <green>Successfully put on a hat!");
            config.addDefault("hat-error-blocked-item","<plugin_prefix> <red>You cannot put <item> on your head");
            config.addDefault("hat-error-binding","<plugin_prefix> <red>Uh oh... Your helmet has curse of binding on it.. That's unfortunate. No hat for you.");
            config.addDefault("anvil-command-other","<plugin_prefix> <gray>Anvil opened for <user>");
            config.addDefault("cartography-command-other","<plugin_prefix> <gray>Cartography table opened for <user>");
            config.addDefault("craft-command-other","<plugin_prefix> <gray>Crafting table opened for <user>");
            config.addDefault("enderchest-command-other","<plugin_prefix> <gray>Enderchest opened for <user>");
            config.addDefault("loom-command-other","<plugin_prefix> <gray>Loom opened for <user>");
            config.addDefault("smithing-command-other","<plugin_prefix> <gray>Smithing table opened for <user>");
            config.addDefault("stonecutter-command-other","<plugin_prefix> <gray>Stonecutter opened for <user>");
            config.addDefault("grindstone-command-other","<plugin_prefix> <gray>Grindstone opened for <user>");
            config.addDefault("rename-command-feedback","<plugin_prefix> <green>You have renamed your item from <oldname> to <newname>");
            config.addDefault("rename-error-input-too-long","<plugin_prefix> <red>Sorry, the limit for the /rename command is <int> characters, excluding formatting and color tags.");
            config.addDefault("fly-enabled-self","<plugin_prefix> <green>Your flight has been enabled");
            config.addDefault("fly-disabled-self","<plugin_prefix> <gray>Your flight has been disabled");
            config.addDefault("fly-enabled-other","<plugin_prefix> <green>You have enabled flight for <user>");
            config.addDefault("fly-disabled-other","<plugin_prefix> <gray>You have disabled flight for <user>");
            config.addDefault("fly-enabled_by-other","<plugin_prefix> <green><sender> has enabled flight for you");
            config.addDefault("fly-disabled_by-other","<plugin_prefix> <gray><sender> has disabled your flight");
            config.addDefault("other-current-walkspeed","<plugin_prefix> <user>'s current walkspeed is <value>");
            config.addDefault("own-current-walkspeed","<plugin_prefix> Your walkspeed is currently set to");
            config.addDefault("walkspeed-set","<plugin_prefix> <green>Your walkspeed has been set to <value>");
            config.addDefault("walkspeed-reset","<plugin_prefix> <green>Your walkspeed has been reset");
            config.addDefault("walkspeed-set-other","<plugin_prefix> <green>You set <user>'s walkspeed to <value>");
            config.addDefault("walkspeed-reset-other","<plugin_prefix> <green><user>'s walkspeed has been reset");
            config.addDefault("walkspeed-set_by-other","<plugin_prefix> <green>Your walkspeed has been set to <value> by <sender>");
            config.addDefault("walkspeed-reset-by-other","<plugin_prefix> <green>Your walkspeed has been reset by <sender>");
            config.addDefault("other-current-flyspeed","<plugin_prefix> <user>'s current flyspeed is <value>");
            config.addDefault("own-current-flyspeed","<plugin_prefix> Your flyspeed is currently set to");
            config.addDefault("flyspeed-set","<plugin_prefix> <green>Your flyspeed has been set to <value>");
            config.addDefault("flyspeed-reset","<plugin_prefix> <green>Your flyspeed has been reset");
            config.addDefault("flyspeed-set-other","<plugin_prefix> <green>You set <user>'s flyspeed to <value>");
            config.addDefault("flyspeed-reset-other","<plugin_prefix> <green><user>'s flyspeed has been reset");
            config.addDefault("flyspeed-set-by-other","<plugin_prefix> <green>Your flyspeed has been set to <value> by <sender>");
            config.addDefault("flyspeed-reset-by-other","<plugin_prefix> <green>Your flyspeed has been reset by <sender>");
            config.addDefault("speed-number-error","<plugin_prefix> <red>Please use a number between -10 and 10");

        }
}
