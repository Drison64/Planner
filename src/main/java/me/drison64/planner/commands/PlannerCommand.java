package me.drison64.planner.commands;

import me.drison64.planner.Main;
import me.drison64.planner.managers.WaitingManager;
import me.drison64.planner.objects.WaitingType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlannerCommand implements CommandExecutor {

    private Main main;
    private WaitingManager waitingManager;

    public PlannerCommand(Main main) {
        this.main = main;
        this.waitingManager = main.getWaitingManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args[0] == null) {
            return false;
        }

        if (args[0].equals("add")) {
            waitingManager.addPlayer(player, WaitingType.ADD);
        }

        return true;
    }

}
