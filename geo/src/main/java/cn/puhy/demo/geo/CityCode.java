package cn.puhy.demo.geo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author puhongyu
 * 2019/4/19 09:35
 */
public class CityCode {

    private static Map<String, String> map = new HashMap<>();

    static {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("C:\\Users\\puhongyu\\Desktop\\geo\\全量城市码.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            String[] lineArray = line.split("\\|");
            if (lineArray.length == 2) {
                map.put(lineArray[0], lineArray[1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getCode("天门市"));
    }

    public static String getCode(String name) {
        return map.get(name);
    }
}
