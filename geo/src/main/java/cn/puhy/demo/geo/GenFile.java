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

    private static String filePath = "C:\\Users\\puhongyu\\Desktop\\geo-1.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = new LinkedList<>();
        try {
            List<String> list = Files.readAllLines(Paths.get(filePath));
            JSONArray jsonArray = JSONArray.parseArray(list.get(0));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                JSONArray array = jsonObject.getJSONArray("geo");

                StringBuilder stringBuilder = new StringBuilder();
                int length = array.size();
                for (int j = 0; j < length; j++) {
                    JSONObject arrayObject = array.getJSONObject(j);
                    String lon = arrayObject.getString("lng");
                    String lat = arrayObject.getString("lat");

                    stringBuilder.append(lon).append(",").append(lat);
                    if (j != length - 1) {
                        stringBuilder.append("|");
                    }
                }
                String line = name + "|" + stringBuilder.toString();
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.write(Paths.get("C:\\Users\\puhongyu\\Desktop\\1.txt"), lines);
    }
}
