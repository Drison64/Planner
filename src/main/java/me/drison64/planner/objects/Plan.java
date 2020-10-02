package me.drison64.planner.objects;

import org.bukkit.Material;
import java.util.ArrayList;
import java.util.List;

public class Plan {

    private Long start;
    private Long duration;
    private String title;
    private List<String> lore;
    private Material material;
    private String remaining;

    public Plan(Long start, Long duration, String title, List<String> lore) {
        this.start = start;
        this.duration = duration;
        this.title = title;
        this.lore = lore;

        Long startTime = getStart();
        Long endTime = getStart() + getDuration();
        Long nowTime = System.currentTimeMillis() / 1000L;

        long toStart = startTime - nowTime;

        if (toStart <= 0) {
            String[] strings = getRemaingTime(endTime);

            this.material = Material.RED_CONCRETE;
            this.remaining = "Ends in: " + strings[0] + ":" + strings[1] + ":" + strings[2] + ":" + strings[3];


        } else if (toStart <= 1799) {
            String[] strings = getRemaingTime(startTime);

            this.material = Material.YELLOW_CONCRETE;
            this.remaining = "Starts in: " + strings[0] + ":" + strings[1] + ":" + strings[2] + ":" + strings[3];

        } else {
            String[] strings = getRemaingTime(startTime);

            this.material = Material.GREEN_CONCRETE;
            this.remaining = "Starts in: " + strings[0] + ":" + strings[1] + ":" + strings[2] + ":" + strings[3];
        }
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

    public Material getMaterial() {
        return material;
    }

    public String[] getRemaingTime(Long to_) {
        Long nowTime = System.currentTimeMillis() / 1000L;

        long to = to_ - nowTime;

        String sec = twoZeros(String.valueOf(to % 60))[0];
        String minutes = twoZeros(String.valueOf(to % 3600 / 60))[0];
        String hours = twoZeros(String.valueOf(to % 86400 / 3600))[0];
        String days = twoZeros(String.valueOf((to / 86400)))[0];
        return new String[]{days, hours, minutes, sec};
    }

    private String[] twoZeros(String... strings) {
        List<String> stringList = new ArrayList<>();
        for (String string : strings) {
            if (string.length() == 1) {
                stringList.add("0" + string);
            } else {
                stringList.add(string);
            }
        }
        return stringList.toArray(new String[0]);
    }

}
