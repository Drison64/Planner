package me.drison64.planner.objects;

import org.bukkit.Material;
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
        return new Plan(start, duration, title, lore);
    }

}
