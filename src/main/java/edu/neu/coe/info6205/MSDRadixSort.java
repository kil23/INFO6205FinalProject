package edu.neu.coe.info6205;

import edu.neu.coe.info6205.utils.FileUtil;
import edu.neu.coe.info6205.utils.SortUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
// n: input size, d: number of distinct elements, complexity - O(n + d*lg(d))
public class MSDRadixSort {
    private Integer maxStringLength;
    private List<String> aux;
    private SortUtils sortUtils;
    public List<String> sort(List<String> xs) {
        if (xs.size() == 0) return xs;
        aux = new ArrayList<>();
        sortUtils = new SortUtils();
        maxStringLength = sortUtils.getMaxStringLength(xs);
        for (int i = 0; i < xs.size(); i++) aux.add("");
        xs = sortUtils.padMSDStrings(xs);
        xs = sort(xs, 0, xs.size() - 1, 0);
        return sortUtils.removeMSDPadding(xs);
    }

    private List<String> sort(List<String> xs, int l, int r, int d) {
        if (d >= maxStringLength) return xs;
        List<Integer> indices = new ArrayList<>();
        // counting step
        Map<Character, Integer> count = sortUtils.getFrequencyTreeMap(xs, l, r, d);

        // TODO: additionally we can use an LinkedHashMap to store the sorted and counted frequencies from the map, and iterate over the in the future steps. (maybe)
        // TODO: use msd radix sort to sort buckets of strings having same lengths, and then use k-way merge of sorted lists https://leetcode.com/problems/merge-k-sorted-lists/
        // populate indices array for iteration
        for (int i: count.values()) indices.add(i);

        // cumulative sum step  ---  for i in range(1, n): a[i] += a[i-1]
        count = sortUtils.cumulativeSum(count);

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