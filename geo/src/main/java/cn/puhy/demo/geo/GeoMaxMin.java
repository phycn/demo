package cn.puhy.demo.geo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class GeoMaxMin {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\puhongyu\\Desktop\\geo\\yaan.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        String[] lonLats = lines.get(0).split(";");
        double[] lons = new double[lonLats.length];
        double[] lats = new double[lonLats.length];
        int i = 0;
        for (String lonLat : lonLats) {
            double lon = Double.parseDouble(lonLat.split(",")[0]);
            double lat = Double.parseDouble(lonLat.split(",")[1]);
            lons[i] = lon;
            lats[i] = lat;
            i++;
        }
        System.out.println(Arrays.stream(lons).max().orElse(0));
        System.out.println(Arrays.stream(lons).min().orElse(0));
        System.out.println(Arrays.stream(lats).max().orElse(0));
        System.out.println(Arrays.stream(lats).min().orElse(0));
    }
}
