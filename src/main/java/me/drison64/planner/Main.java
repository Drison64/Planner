package me.drison64.planner;

import me.drison64.inventoryapi.InventoryAPI;
import me.drison64.inventoryapi.InventoryManager;
import me.drison64.planner.commands.PlannerCommand;
import me.drison64.planner.configurations.Config;
import me.drison64.planner.configurations.Data;
import me.drison64.planner.listeners.PlayerListener;
import me.drison64.planner.managers.ConfigManager;
import me.drison64.planner.managers.PlanManager;
import me.drison64.planner.managers.RefreshManager;
import me.drison64.planner.managers.WaitingManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private ConfigManager configManager;

    private WaitingManager waitingManager;

    private InventoryManager inventoryManager;

    private RefreshManager refreshManager;

    private PlanManager planManager;

    private InventoryAPI inventoryAPI;

    @Override
    public void onEnable() {

        configManager = new ConfigManager(this);

        refreshManager = new RefreshManager(this);

        waitingManager = new WaitingManager();

        planManager = new PlanManager();

        inventoryAPI = new InventoryAPI(this);

        inventoryManager = inventoryAPI.getInventoryManager();

        configManager.registerConfig(new Config(this));
        configManager.registerConfig(new Data(this));

        getCommand("planner").setExecutor(new PlannerCommand(this));

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);

    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public WaitingManager getWaitingManager() {
        return waitingManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public RefreshManager getRefreshManager() {
        return refreshManager;
    }

    public PlanManager getPlanManager() {
        return planManager;
    }

}
