package me.drison64.planner.objects;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class PlanBuilder {

    private Long start;
    private Long duration;
    private String title;
    private List<String> lore;

    public PlanBuilder setStart(Long start) {
        this.start = start;
        return this;
    }

    public PlanBuilder setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public PlanBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PlanBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public Plan build() {
        String _title = ChatColor.translateAlternateColorCodes('&', title);
        List<String> _lore = new ArrayList<>();
        lore.forEach(l -> _lore.add(ChatColor.translateAlternateColorCodes('&', l)));
        return new Plan(start, duration, _title, _lore);
    }

}
