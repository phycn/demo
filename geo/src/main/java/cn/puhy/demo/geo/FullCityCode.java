package cn.puhy.demo.geo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * 找出后两位没有00的地区，比如469021,没有469000
 */
public class FullCityCode {

    private static String filePath = "C:\\Users\\puhongyu\\Desktop\\geo\\gaode-city-code.txt";

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> citys = new LinkedList<>();
        List<String> districts = new LinkedList<>();

        List<String> newLines = new LinkedList<>();

        for (String line : lines) {
            String[] codeCity = line.split("\\|");
            if (codeCity.length != 2) {
                continue;
            }
            if (!codeCity[1].endsWith("00")) {
                newLines.add(line);
            }
        }
        Files.write(Paths.get("C:\\Users\\puhongyu\\Desktop\\gaode-city-code-3.txt"), newLines);
    }
}
