package cn.puhy.demo.geo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author puhongyu
 * 2019/4/25 14:23
 */
public class CheckPoint {

    private static String filePath = "/Users/puhongyu/phy/tmp/city/呼和浩特市.txt";

    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(filePath));
        String[] lonLats = list.get(0).split(";");
        System.out.println(lonLats.length);

        Set<String> set = new HashSet<>();
        for (String lonLat : lonLats) {
            boolean flag = set.add(lonLat);
            if (!flag) {
                System.out.println(lonLat);
            }
        }
        System.out.println(set.size());
    }
}
