package edu.neu.coe.info6205.utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortUtils {
    private final Character paddingChar = ' ';

    public Integer getMaxStringLength(List<String> xs) {
        if (xs == null || xs.isEmpty()) return -1;
        Integer maxStringLength = xs.get(0).length();
        for (int i = 0; i < xs.size(); i++) {
            maxStringLength = Math.max(maxStringLength, xs.get(i).length());
        }
        return maxStringLength;
    }

    public List<String> padMSDStrings(List<String> xs) {
        final Integer maxStringLength = getMaxStringLength(xs);
        xs = xs.stream().map(currStr -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < maxStringLength - currStr.length(); i++) sb.append(paddingChar);
            return currStr + sb.toString();
        }).collect(Collectors.toList());
        return xs;
    }

    public List<String> removeMSDPadding(List<String> xs) {
        return xs.stream().map(curStr -> curStr.trim()).collect(Collectors.toList());
    }

    public Map<Character, Integer> getFrequencyTreeMap(List<String> xs, int l, int r, int d) {
        if (l > r) return null;
        Map<Character, Integer> count = new TreeMap<>();
        for (int i = l; i <= r; i++) {
            Character c = xs.get(i).charAt(d);
            if (count.containsKey(c)) count.put(c, count.get(c) + 1);
            else count.put(c, 1);
        }
        return count;
    }

    public Map<Character, Integer> cumulativeSum(Map<Character, Integer> count) {
        Integer sm = 0;
        for (Map.Entry<Character, Integer> entry: count.entrySet()) {
            entry.setValue(sm + entry.getValue());
            sm = entry.getValue();
        }
        return count;
    }
}
