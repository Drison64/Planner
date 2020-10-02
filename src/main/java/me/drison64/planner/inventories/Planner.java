package me.drison64.planner.inventories;

import me.drison64.inventoryapi.CustomInventory;
import me.drison64.inventoryapi.ItemStackUtils;
import me.drison64.planner.Main;
import me.drison64.planner.managers.PlanManager;
import me.drison64.planner.objects.ConfigType;
import me.drison64.planner.objects.Plan;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Planner extends CustomInventory {

    private Main main;
    private PlanManager planManager;

    private Block block;
    private Integer page;
    private Integer pages;

    public Planner(Main main, Block block, Integer page) {
        this.main = main;
        this.planManager = main.getPlanManager();

        this.block = block;
        this.page = page;
    }

    @Override
    protected void init(HashMap<Integer, ItemStack> hashMap) {
        main.getRefreshManager().register(player, this);
        size = 54;
        title = "Planner";

        int[] slots = {45,46,49,52,53};

        main.getConfigManager().getConfig(ConfigType.DATA).reloadConfig();
        List<Plan> actionslist = planManager.getPlans(main.getConfigManager().getConfig(ConfigType.DATA).get().getStringList("planners." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".plans"));

        int pages = actionslist.size() / 45;
        if (actionslist.size() % 45 != 0) pages++;
        if (pages == 0) {
            pages++;
        }

        title = "Planner - Plans (" + page + "/" + pages + "" +")";
        size = 54;

        this.pages = pages;

        for (int i : slots) {
            set(i, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }

        if (page < pages) {
            set(47, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/d1b62db5c0a3fa1ef441bf7044f511be58bedf9b6731853e50ce90cd44fb69", "Down", Arrays.asList("")));
        } else {
            set(47, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }
        if (page > 1) {
            set(51, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/14a5667ef7285c9225fc267d45117eab5478c786bd5af0a199c29a2c14c1f", "Up", Arrays.asList("")));
        } else {
            set(51, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }
        if (!(actionslist.size() < 1)) {
            set(48, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/4e4b8b8d2362c864e062301487d94d3272a6b570afbf80c2c5b148c954579d46", "Remove", Arrays.asList("")));
        } else {
            set(48, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }
        set(50, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/b056bc1244fcff99344f12aba42ac23fee6ef6e3351d27d273c1572531f", "Add", Arrays.asList("")));

        for (int i = 0; i < 45; i++) {
            if (i + (45 * (page - 1)) >= actionslist.size()) {
                break;
            }

            if (!(actionslist.get(i + (45 * (page - 1))) == null)) {
                int id = i + (45 * (page - 1));
                Plan plan = actionslist.get(id);

                set(i, ItemStackUtils.mkitem(1, plan.getMaterial(), plan.getTitle(), plan.getLore()));

            } else {
                break;
            }
        }

    }

    @Override
    protected void fire(Event event) {
        if (event instanceof InventoryCloseEvent) {
            InventoryCloseEvent e = (InventoryCloseEvent) event;
            main.getRefreshManager().unregister((Player) e.getPlayer());
        }

        if (event instanceof PlayerQuitEvent) {
            PlayerQuitEvent e = (PlayerQuitEvent) event;
            main.getRefreshManager().unregister(e.getPlayer());
        }

        if (event instanceof InventoryClickEvent) {
            InventoryClickEvent e = (InventoryClickEvent) event;
            e.setCancelled(true);

            if (page < pages) {

                if (e.getRawSlot() == 47) {
                    CustomInventory inventory = new Planner(main, block, page + 1);

                    main.getInventoryManager().open(inventory, player);
                }

            }

            if (page > 1) {

                if (e.getRawSlot() == 51) {
                    CustomInventory inventory = new Planner(main, block, page - 1);

                    main.getInventoryManager().open(inventory, player);
                }

            }
        }
    }

}
