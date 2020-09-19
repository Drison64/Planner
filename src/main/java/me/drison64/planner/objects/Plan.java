package me.drison64.planner.objects;

import java.util.List;

public class Plan {

    private Long start;
    private Long duration;
    private String title;
    private List<String> lore;

    public Plan(Long start, Long duration, String title, List<String> lore) {
        this.start = start;
        this.duration = duration;
        this.title = title;
        this.lore = lore;
    }

    public Long getStart() {
        return start;
    }

    public Long getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLore() {
        return lore;
    }

}
