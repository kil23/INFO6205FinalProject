package edu.neu.coe.info6205;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MSDRadixSortTest {
    @Test
    public void sortTest() {
        List<String> input1 = new ArrayList<>(), input2;
        input1.add("uiytu");
        input1.add("zuwkak");
        input1.add("yhtzybe");
        input1.add("gxprcuhjy");
        input1.add("rlhtefjsmq");
        input1.add("iwpwc");
        input1.add("pogamb");
        input1.add("lopx");
        input1.add("yabhipor");
        input1.add("ysbqxmist");
        input1.add("ioonvndb");
        input1.add("senhjgqlc");
        input1.add("awoys");
        input1.add("bvzcbq");
        input1.add("bfzyq");
        input1.add("cnkpnahkhg");
        input1.add("xswxk");
        input2 = new ArrayList<>(input1);
        input1 = (new MSDRadixSort()).sort(input1);
        Collections.sort(input2);
        for (int i = 0; i < input1.size(); i++) {
            assertEquals(input2.get(i), input1.get(i));
        }
    }
}
