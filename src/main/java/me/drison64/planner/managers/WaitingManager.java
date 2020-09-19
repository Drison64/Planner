package me.drison64.planner.managers;

import me.drison64.planner.Main;
import me.drison64.planner.objects.WaitingType;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WaitingManager {


    private HashMap<Player, WaitingType> waitingPlayers;

    public WaitingManager() {
        waitingPlayers = new HashMap<>();
    }

    public void addPlayer(Player player, WaitingType waitingType) {
        waitingPlayers.put(player, waitingType);
    }

    public void removePlayer(Player player) {
        waitingPlayers.remove(player);
    }

    public WaitingType getWaiting(Player player) {
        return waitingPlayers.get(player);
    }

}
