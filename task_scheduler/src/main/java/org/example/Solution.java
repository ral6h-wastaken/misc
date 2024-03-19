package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    private Solution() {
    }

    public static int leastInterval(char[] tasks, int n) {
        int res = 0;
        StringBuilder resStringBuilder = new StringBuilder();

        Map<Character, Integer> map = getCountMap(tasks);
        int totalTasksLeft = map.values().stream().reduce(0, Integer::sum);
        Comparator<Map.Entry<Character, Integer>> myComp = (x, y) -> (x.getValue().compareTo(y.getValue()) * -1);
        List<Map.Entry<Character, Integer>> orderedTasks = map.entrySet().stream().filter(e -> e.getValue() > 0)
                .sorted(myComp)
                .collect(Collectors.toList());

        boolean check = (totalTasksLeft > 0);
        while (check) {
            for (int i = 0; i <= n; i++) {
                if (!check)
                    break;
                if (i < orderedTasks.size() && orderedTasks.get(i).getValue() > 0) {
                    resStringBuilder.append(orderedTasks.get(i).getKey());
                    orderedTasks.get(i).setValue(orderedTasks.get(i).getValue() - 1);
                    orderedTasks.set(i, orderedTasks.get(i));
                    totalTasksLeft = orderedTasks.stream().map(Map.Entry::getValue).reduce(0, Integer::sum);
                    check = (totalTasksLeft > 0);
                } else {
                    resStringBuilder.append('*');
                }
            }
            orderedTasks = orderedTasks.stream().sorted(myComp).collect(Collectors.toList());
        }
        String resString = resStringBuilder.toString();
        System.out.println(resString);

        res = resString.length() + totalTasksLeft;
        return res;
    }

    private static Map<Character, Integer> getCountMap(char[] tasks) {
        Map<Character, Integer> map = new HashMap<>();
        for (char current : tasks) {
            if (map.containsKey(current))
                map.put(current, map.get(current) + 1);
            else
                map.put(current, 1);
        }
        return map;
    }
}