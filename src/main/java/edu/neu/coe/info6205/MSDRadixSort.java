package edu.neu.coe.info6205;

import edu.neu.coe.info6205.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
// n: input size, d: number of distinct elements, complexity - O(n + d*lg(d))
public class MSDRadixSort {
    private final Character paddingChar = ' ';
    private Integer maxStringLength;
    private List<String> aux;
    public List<String> sort(List<String> xs) {
        if (xs.size() == 0) return xs;
        aux = new ArrayList<>();
        maxStringLength = xs.get(0).length();
        for (int i = 0; i < xs.size(); i++) {
            aux.add("");
            maxStringLength = Math.max(maxStringLength, xs.get(i).length());
        }
        xs = xs.stream().map(currStr -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < maxStringLength - currStr.length(); i++) sb.append(paddingChar);
            return currStr + sb.toString();
        }).collect(Collectors.toList());
        xs = sort(xs, 0, xs.size() - 1, 0);
        return xs.stream().map(curStr -> curStr.trim()).collect(Collectors.toList());
    }

    private List<String> sort(List<String> xs, int l, int r, int d) {
        if (d >= maxStringLength) return xs;
        Map<Character, Integer> count = new TreeMap<>();
        List<Integer> indices = new ArrayList<>();
        // counting step
        for (int i = l; i <= r; i++) {
            Character c = xs.get(i).charAt(d);
            if (count.containsKey(c)) count.put(c, count.get(c) + 1);
            else count.put(c, 1);
        }
        // TODO: additionally we can use an LinkedHashMap to store the sorted and counted frequencies from the map, and iterate over the in the future steps. (maybe)
        // TODO: use msd radix sort to sort buckets of strings having same lengths, and then use k-way merge of sorted lists https://leetcode.com/problems/merge-k-sorted-lists/
        // populate indices array for iteration
        for (int i: count.values()) indices.add(i);
//        count.entrySet().stream().forEach(System.out::println);
        // cumulative sum step  ---  for i in range(1, n): a[i] += a[i-1]
        Integer sm = 0;
        for (Map.Entry<Character, Integer> entry: count.entrySet()) {
            entry.setValue(sm + entry.getValue());
            sm = entry.getValue();
        }
        // populate the aux array from l to r
        for (int i = l; i <= r; i++) {
            Character c = xs.get(i).charAt(d);
            Integer newIdx = l + count.get(c) - 1;
            aux.set(newIdx, xs.get(i));
            count.put(c, count.get(c) - 1);
        }
        // copy aux to xs
        for (int i = l; i <= r; i++) {
            xs.set(i, aux.get(i));
        }
        // recursive step
        int lo = l;
        for (int i = 0; i < indices.size(); i++) {
            this.sort(xs, lo, lo + indices.get(i) - 1, d + 1);
            lo = lo + indices.get(i);
        }
        return xs;
    }

    public static void main(String []args) {
        FileUtil fu = new FileUtil();
        List<String> a = fu.readFile(System.getProperty("user.dir") + "\\src\\main\\resources\\englishStrings.txt");
//        List<String> a = fu.readFile(System.getProperty("user.dir") + "\\src\\main\\resources\\shuffledChinese.txt");
        a = (new MSDRadixSort().sort(a));
        a.stream().forEach(System.out::println);
    }
}