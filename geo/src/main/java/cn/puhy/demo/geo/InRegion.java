package cn.puhy.demo.geo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author puhongyu
 * 2019/4/25 12:38
 */
public class InRegion {

    private static String filePath = "C:\\Users\\puhongyu\\Desktop\\tmp\\振兴区.txt";

    public static void main(String[] args) throws IOException {

        List<String> list = Files.readAllLines(Paths.get(filePath));

        String[] regions = list.get(0).split("\\|");
        for (String region : regions) {
            String[] lonLats1 = region.split(";");
            List<String> list1 = Arrays.stream(lonLats1).distinct().collect(Collectors.toList());
            String[] lonLats = new String[list1.size()];
            lonLats = list1.toArray(lonLats);
//            System.out.println(lonLats.length);
            int length = lonLats.length;
            double[] lons = new double[length];
            double[] lats = new double[length];

            for (int i = 0; i < length; i++) {
                String lonLat = lonLats[i];
                String[] lonLatArray = lonLat.split(",");
                lons[i] = Double.parseDouble(lonLatArray[0]);
                lats[i] = Double.parseDouble(lonLatArray[1]);
            }
            // 30.6491364485,104.0487670898
            System.out.println(inRegion(lons, lats, 124.36234, 40.09293));
        }
    }

    private static boolean inRegion(double[] lons, double[] lats, double lon, double lat) {
        int count = lons.length;
        boolean flag = false;
        for (int i = 0, j = count - 1; i < count; j = i++) {
            if (((lats[i] > lat) != (lats[j] > lat)) &&
                    (lon < (lons[j] - lons[i]) * (lat - lats[i]) / (lats[j] - lats[i]) + lons[i])) {
                flag = !flag;
            }
        }
        return flag;
    }
}
