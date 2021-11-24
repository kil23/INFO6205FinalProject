package edu.neu.coe.info6205.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SortUtilsTest {
    @Test
    public void getMaxStringLengthTest1() {
        List<String> xs = new ArrayList<>();
        xs.add("asdafasd");
        xs.add("asd");
        xs.add("asdsd");
        xs.add("a");
        xs.add("as");
        assertEquals(Integer.valueOf(8), (new SortUtils()).getMaxStringLength(xs));
    }

    @Test
    public void getMaxStringLengthTest2() {
        List<String> xs = new ArrayList<>();
        assertEquals(Integer.valueOf(-1), (new SortUtils()).getMaxStringLength(xs));
        xs.add("a");
        assertEquals(Integer.valueOf(1), (new SortUtils()).getMaxStringLength(xs));
    }

    @Test
    public void padMSDStringsTest() {
        List<String> xs = new ArrayList<>();
        xs.add("");
        xs.add("dafasd");
        List<String> xs1 = (new SortUtils()).padMSDStrings(new ArrayList<>(xs));
        xs.set(0, "      ");
        for (int i = 0; i < 2; i++) {
            assertEquals(xs.get(i), xs1.get(i));
        }
    }

    @Test
    public void removeMSDPaddingTest() {
        List<String> xs = new ArrayList<>();
        xs.add("      ");
        xs.add("dafasd");
        List<String> xs1 = (new SortUtils()).removeMSDPadding(new ArrayList<>(xs));
        xs.set(0, "");
        for (int i = 0; i < 2; i++) {
            assertEquals(xs.get(i), xs1.get(i));
        }
    }

    @Test
    public void getFrequencyTreeMapTest() {
        SortUtils sortUtils = new SortUtils();
        List<String> xs = new ArrayList<>();
        xs.add("absd");
        xs.add("abcd");
        xs.add("abde");
        xs.add("zsdc");
        assertEquals(null, sortUtils.getFrequencyTreeMap(xs, 3, 2, 1));
        Map<Character, Integer> mp = sortUtils.getFrequencyTreeMap(xs, 1, 1, 1);
        assertEquals(Integer.valueOf(1), mp.get('b'));
    }

    @Test
    public void cumulativeSumTest() {
        SortUtils sortUtils = new SortUtils();
        List<String> xs = new ArrayList<>();
        xs.add("absd");
        xs.add("abcd");
        xs.add("abde");
        xs.add("zsdc");
        Map<Character, Integer> mp = sortUtils.getFrequencyTreeMap(xs, 0, 3, 2);
        mp = sortUtils.cumulativeSum(mp);
        assertEquals(Integer.valueOf(3), mp.get('d'));
        assertEquals(Integer.valueOf(4), mp.get('s'));
        assertEquals(Integer.valueOf(1), mp.get('c'));
    }
}