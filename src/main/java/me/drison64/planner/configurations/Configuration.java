package me.drison64.planner.configurations;

import me.drison64.planner.Main;
import me.drison64.planner.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private Main main;
    private ConfigManager configManager;

    public Configuration(Main main) {
        this.main = main;
        this.configManager = main.getConfigManager();
    }

    protected File file;
    protected FileConfiguration config;
    protected String fileName;

    public void createConfig(String fileName) {
        this.fileName = fileName;
        file = new File(Bukkit.getPluginManager().getPlugin("Planner").getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration get() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

}
