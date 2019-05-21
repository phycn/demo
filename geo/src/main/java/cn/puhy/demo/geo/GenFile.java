package cn.puhy.demo.geo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GenFile {

    private static String filePath = "C:\\Users\\puhongyu\\Desktop\\geo-20190517.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = new LinkedList<>();
        try {
            List<String> list = Files.readAllLines(Paths.get(filePath));
            JSONArray jsonArray = JSONArray.parseArray(list.get(0));
            for (int i = 0; i < jsonArray.size(); i++) {

                StringBuilder sb1 = new StringBuilder();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                JSONArray array = jsonObject.getJSONArray("geo");
                for (int j = 0, length = array.size(); j < length; j++) {
                    JSONArray array1 = array.getJSONArray(j);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int k = 0, length1 = array1.size(); k < length1; k++) {
                        JSONObject jsonObject1 = array1.getJSONObject(k);
                        String lon = jsonObject1.getString("lng");
                        String lat = jsonObject1.getString("lat");
                        stringBuilder.append(lon).append(",").append(lat);
                        if (k != length1 - 1) {
                            stringBuilder.append(";");
                        }
                    }
                    sb1.append(stringBuilder);
                    if (j != length - 1) {
                        sb1.append("|");
                    }
                }

                String line = name + "|" + CityCode.getCode(name) + "|" + sb1.toString();
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.write(Paths.get("C:\\Users\\puhongyu\\Desktop\\geo-sample.txt"), lines);
    }
}
