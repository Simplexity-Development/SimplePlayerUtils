package adhdmc.simpleplayerutils.config;

import adhdmc.simpleplayerutils.SimplePlayerUtils;
import adhdmc.simpleplayerutils.util.SPUMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class LocaleBuilder {

        private static LocaleBuilder instance;
        private final String fileName = "locale.yml";
        private final  File localeFile = new File(SimplePlayerUtils.getInstance().getDataFolder(), fileName);
        private final FileConfiguration localeConfig = new YamlConfiguration();


        private LocaleBuilder() {
            if (!localeFile.exists()) SimplePlayerUtils.getInstance().saveResource(fileName, false);
            reloadLocale();
        }

        public static LocaleBuilder getInstance() {
            if (instance == null) instance = new LocaleBuilder();
            return instance;
        }

        public FileConfiguration getLocale() { return localeConfig; }

        public void reloadLocale() {
            try { localeConfig.load(localeFile); }
            catch (IOException | InvalidConfigurationException e) { e.printStackTrace(); }
            Set<String> keys = localeConfig.getKeys(false);
            for (String key : keys) {
                try {
                    SPUMessage message = SPUMessage.valueOf(key);
                    message.setMessage(localeConfig.getString(key, message.getMessage()));
                } catch (IllegalArgumentException e) {
                    SimplePlayerUtils.getSPULogger().warning(SPUMessage.LOGGER_INVALID_LOCALE_KEY + key);
            }
        }
    }
}
