package edu.neu.coe.info6205.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class FileUtil {
    public List<String> readFile(String path) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String line;
            while((line = in.readLine()) != null) {
                result.add(line);
            }
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void writeFile(List<String> objects, String outputPath) {
        try {
            FileWriter fw = new FileWriter(outputPath);
            BufferedWriter out = new BufferedWriter(fw);
            for (String s: objects) {
                out.write(s);
                out.newLine();
            }
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        FileUtil fu = new FileUtil();
//        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        List<String> chinese = fu.readFile(System.getProperty("user.dir") + "\\src\\main\\resources\\shuffledChinese.txt");
//        Set<Long> st = new HashSet<>();
//        chinese.stream().sorted().forEach(x->{for(int i = 0; i < x.length(); i++) st.add(Long.valueOf(x.charAt(i)));});
//        System.out.println("unique characters in data set: " + st.size());
//        st.stream().sorted().forEach(System.out::println);
        Map<Integer, Integer> mp = new HashMap<>();
        chinese.stream().sorted().forEach(x->{
            if (mp.containsKey(x.length())) mp.put(x.length(), mp.get(x.length()) + 1);
            else mp.put(x.length(), 1);
        });
//        System.out.println("unique characters in data set: " + st.size());
        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
