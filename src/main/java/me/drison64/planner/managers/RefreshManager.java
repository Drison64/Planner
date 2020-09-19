package me.drison64.planner.managers;

import me.drison64.inventoryapi.CustomInventory;
import me.drison64.inventoryapi.InventoryManager;
import me.drison64.planner.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RefreshManager {

    private Main main;
    private InventoryManager inventoryManager;
    private HashMap<Player, CustomInventory> refreshingInventories;
    private Integer taskID;

    public RefreshManager(Main main) {
        this.main = main;
        this.inventoryManager = main.getInventoryManager();
        this.refreshingInventories = new HashMap<>();

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
            for (Player player : this.refreshingInventories.keySet()) {
                refreshingInventories.get(player).refresh();
            }
        }, 0L , 20L);

    }

    public void register(Player player, CustomInventory customInventory) {
        refreshingInventories.put(player, customInventory);
    }

    public void unregister(Player player) {
        refreshingInventories.remove(player);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(this.taskID);
    }



}
