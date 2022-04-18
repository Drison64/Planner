package me.drison64.planner.listeners;

import me.drison64.inventoryapi.CustomInventory;
import me.drison64.inventoryapi.InventoryManager;
import me.drison64.planner.Main;
import me.drison64.planner.inventories.Planner;
import me.drison64.planner.managers.ConfigManager;
import me.drison64.planner.managers.RefreshManager;
import me.drison64.planner.managers.WaitingManager;
import me.drison64.planner.objects.ConfigType;
import me.drison64.planner.objects.WaitingType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Arrays;

public class PlayerListener implements Listener {

    private Main main;
    private WaitingManager waitingManager;
    private InventoryManager inventoryManager;
    private ConfigManager configManager;
    private RefreshManager refreshManager;

    public PlayerListener(Main main) {
        this.main = main;
        this.waitingManager = main.getWaitingManager();
        this.inventoryManager = main.getInventoryManager();
        this.configManager = main.getConfigManager();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (!(e.getHand() == EquipmentSlot.HAND)) return;

            if (waitingManager.getWaiting(player) == WaitingType.ADD) {

                e.setCancelled(true);

                waitingManager.removePlayer(player);

                String id = String.valueOf(e.getClickedBlock().getLocation().getBlockX()) + String.valueOf(e.getClickedBlock().getLocation().getBlockY()) + String.valueOf(e.getClickedBlock().getLocation().getBlockZ());

                configManager.getConfig(ConfigType.DATA).get().set("planners." + id + ".plans", Arrays.asList(""));

                configManager.getConfig(ConfigType.DATA).save();

                CustomInventory inventory = new Planner(main, e.getClickedBlock(), 1);

                inventoryManager.open(inventory, player);

            } else {

                String id = String.valueOf(e.getClickedBlock().getLocation().getBlockX()) + String.valueOf(e.getClickedBlock().getLocation().getBlockY()) + String.valueOf(e.getClickedBlock().getLocation().getBlockZ());

                if (!(configManager.getConfig(ConfigType.DATA).get().isSet("planners." + id))) return;

                e.setCancelled(true);

                CustomInventory inventory = new Planner(main, e.getClickedBlock(), 1);

                inventoryManager.open(inventory, player);

            }

        }

    }

}
