package cn.puhy.demo.geo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author puhongyu
 * 2019/4/25 12:38
 */
public class InRegion {

    private static String filePath = "/Users/puhongyu/phy/tmp/city/乌兰察布市.txt";

    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(filePath));
        String[] lonLats = list.get(0).split(";");

        int length = lonLats.length;
        double[] lons = new double[length];
        double[] lats = new double[length];

        for (int i = 0; i < length; i++) {
            String lonLat = lonLats[i];
            String[] lonLatArray = lonLat.split(",");
            lons[i] = Double.parseDouble(lonLatArray[0]);
            lats[i] = Double.parseDouble(lonLatArray[1]);
        }
        System.out.println(Arrays.stream(lons).max().orElse(0));
        System.out.println(Arrays.stream(lons).min().orElse(0));
        System.out.println(Arrays.stream(lats).max().orElse(0));
        System.out.println(Arrays.stream(lats).min().orElse(0));
        // 111.4288330078 40.7701418259
        System.out.println(inRegion(lons, lats, 111.7519900000, 40.8414900000));
    }

    private static boolean inRegion(double[] lons, double[] lats, double lon, double lat) {
        int count = lons.length;
        for (int i = 0, j = count - 1; i < count; j = i++) {
            if (((lats[i] > lat) != (lats[j] > lat)) &&
                    (lon < (lons[j] - lons[i]) * (lat - lats[i]) / (lats[j] - lats[i]) + lons[i])) {
                return true;
            }
        }
        return false;
    }
}
