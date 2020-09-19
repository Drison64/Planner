package me.drison64.planner.objects;

import me.drison64.planner.configurations.Config;
import me.drison64.planner.configurations.Configuration;
import me.drison64.planner.configurations.Data;

public enum ConfigType {

    CONFIG(Config.class),
    DATA(Data.class);

    private Class<? extends Configuration> clazz;

    ConfigType(Class<? extends Configuration> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Configuration> getClazz() {
        return clazz;
    }
}
