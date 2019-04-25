package cn.puhy.demo.geo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * @author puhongyu
 * 2019/4/25 11:31
 */
public class GaodeFileToSimple {

    private static String filePath = "/Users/puhongyu/phy/tmp/city/乌兰察布市.txt";
    private static String name = "乌兰察布市";

    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(filePath));
        String[] lonLats = list.get(0).split(";");

        List<String> lines = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, length = lonLats.length; i < length; i++) {
            String lonLat = lonLats[i];
            String[] lonLatArray = lonLat.split(",");
            String lon = lonLatArray[0];
            String lat = lonLatArray[1];
            stringBuilder.append(lon).append(",").append(lat);
            if (i != length - 1) {
                stringBuilder.append("|");
            }
        }
        String code = CityCode.getCode(name);
        String line = name + "|" + code + "|" + stringBuilder.toString();
        lines.add(line);

        Files.write(Paths.get("/Users/puhongyu/phy/tmp/geo-simple.txt"), lines);
    }
}
