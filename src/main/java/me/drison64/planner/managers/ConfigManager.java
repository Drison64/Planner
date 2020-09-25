package me.drison64.planner.managers;


import me.drison64.planner.Main;
import me.drison64.planner.configurations.Configuration;
import me.drison64.planner.objects.ConfigType;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private Main main;

    private List<Configuration> registeredConfigurations = new ArrayList<>();

    public ConfigManager(Main main) {
        this.main = main;
    }

    public void registerConfig(Configuration configuration) {
        registeredConfigurations.add(configuration);
    }

    public Configuration getConfig(Class<? extends Configuration> clazz) {
        for (int i = 0; i < registeredConfigurations.size(); i++) {
            if (registeredConfigurations.get(i).getClass() == clazz) {
                return registeredConfigurations.get(i);
            }
        }
        return null;
    }

    public Configuration getConfig(ConfigType type) {
        return getConfig(type.getClazz());
    }

}
