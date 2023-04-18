package com.te4nick.addonman.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.io.ConfigWriter;
import com.electronwill.nightconfig.core.io.ParsingMode;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.core.utils.UnmodifiableConfigWrapper;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlWriter;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.HashMap;

public class AddonConfigManager {

    //private HashMap<String, String>

    public AddonConfigManager() {
        boolean configFolderCreated = new File("/config/addonman").mkdirs();
        if (configFolderCreated) {
            File configClientFile = new File("/config/addonman/addonman-client.toml");
            if (!configClientFile.exists()) {
                ConfigWriter clientConfigWriter = new TomlWriter();
                CommentedConfig standardConfigClient = TomlFormat.newConfig();
                standardConfigClient.add("path", "/config/addonman");
                standardConfigClient.setComment("path", "Addons packs directory");
                clientConfigWriter.write(standardConfigClient.unmodifiable(), configClientFile, WritingMode.REPLACE);
            }
        }
    }
}
