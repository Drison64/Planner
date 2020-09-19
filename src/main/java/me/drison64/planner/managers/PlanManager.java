package me.drison64.planner.managers;

import me.drison64.planner.objects.Plan;
import me.drison64.planner.objects.PlanBuilder;

import java.util.*;

public class PlanManager {

    public PlanManager() {
    }

    public List<Plan> getPlans(List<String> plans) {

        HashMap<Long, Plan> red, yellow, green;
        red = yellow = green = new HashMap<>();

        System.out.println("PLANSPLANSMJAFFGUW: " + plans.size());

        for (String s : plans) {
            String[] values = s.split("/");
            Calendar startCalendar = getCalendarFromTime(values[0], values[1], values[2], values[3], values[4], values[5]);
            Long startTime = startCalendar.getTime().getTime() / 1000L;
            Long endTime = (startCalendar.getTime().getTime() / 1000L) + (Integer.parseInt(values[6]) * 60);
            Long nowTime = System.currentTimeMillis() / 1000L;

            Plan plan = new PlanBuilder().setStart(startTime).setDuration(endTime - startTime).setTitle("pes").build();

            //System.out.println("starttime: " + startTime);
            //System.out.println("endtime: " + endTime);
            //System.out.println("nowtime: " + nowTime);

            long toStart = startTime - nowTime;
            long toEnd = endTime - nowTime;

            if (toEnd < 0) {
                continue;
            }

            System.out.println("tostart: " + toStart);
            if (toStart <= 0) {
                red.put(endTime, plan);
                System.out.println(plan.getStart() + " " + plan.getDuration());
            } else if (toStart <= 1799) {
                yellow.put(startTime, plan);
                System.out.println(plan.getStart() + " " + plan.getDuration());
            } else {
                green.put(startTime, plan);
                System.out.println(plan.getStart() + " " + plan.getDuration());
            }
        }

        //bubbleSort(red).forEach(e -> System.out.println(e.getStart() + " " + e.getDuration()));
        //bubbleSort(yellow).forEach(e -> System.out.println(e.getStart() + " " + e.getDuration()));
        //bubbleSort(green).forEach(e -> System.out.println(e.getStart() + " " + e.getDuration()));


        List<Plan> outputPlans = new ArrayList<>();
        outputPlans.addAll(bubbleSort(red));
        outputPlans.addAll(bubbleSort(yellow));
        outputPlans.addAll(bubbleSort(green));
        return outputPlans;
    }

    private Calendar getCalendarFromTime(String day, String month, String year, String hour, String minute, String second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        calendar.set(Calendar.SECOND, Integer.parseInt(second));
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public List<Plan> bubbleSort(HashMap<Long, Plan> input) {

        List<Long> longList = new ArrayList<>(input.keySet());
        List<Plan> planInputList = new ArrayList<>(input.values());

        Long[] longArray = longList.toArray(new Long[0]);
        List<Plan> planList = planInputList;

        boolean sorted = false;

        long longTemp;
        Plan planTemp;

        while(!sorted) {
            sorted = true;
            for (int i = 0; i < input.size() - 1; i++) {
                if (longArray[i] > longArray[i+1]) {
                    longTemp = longArray[i];
                    planTemp = planList.get(i);

                    longArray[i] = longArray[i+1];
                    planList.set(i, planList.get(i+1));

                    longArray[i+1] = longTemp;
                    planList.set(i+1, planTemp);

                    sorted = false;
                }
            }
        }
        return planList;
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
