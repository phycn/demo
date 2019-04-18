package cn.puhy.geo.data.impl;

import cn.puhy.geo.data.BorderPointSource;
import cn.puhy.geo.model.GeoInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("borderPointSourceFromSingleFile")
public class BorderPointSourceFromSingleFile implements BorderPointSource {

    private String filePath = "C:\\Users\\puhongyu\\Desktop\\geo-3.txt";

    @Override
    public Map<String, GeoInfo> generateGeoInfoMap() {
        Map<String, GeoInfo> map = new HashMap<>();
        try {
            List<String> list = Files.readAllLines(Paths.get(filePath));
            JSONArray jsonArray = JSONArray.parseArray(list.get(0));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                System.out.println(name);
                JSONArray array = jsonObject.getJSONArray("geo");

                int length = array.size();
                double[] borderLons = new double[length];
                double[] borderLats = new double[length];

                for (int j = 0; j < length; j++) {
                    JSONObject arrayObject = array.getJSONObject(j);
                    borderLons[j] = Double.parseDouble(arrayObject.getString("lng"));
                    borderLats[j] = Double.parseDouble(arrayObject.getString("lat"));
                }

                GeoInfo geoInfo = new GeoInfo();
                geoInfo.setCode(name);
                geoInfo.setName(name);
                geoInfo.setBorderLons(borderLons);
                geoInfo.setBorderLats(borderLats);
                geoInfo.setMaxLon(Arrays.stream(borderLons).max().orElse(0));
                geoInfo.setMinLon(Arrays.stream(borderLons).min().orElse(0));
                geoInfo.setMaxLat(Arrays.stream(borderLats).max().orElse(0));
                geoInfo.setMinLat(Arrays.stream(borderLats).min().orElse(0));

                map.put(name, geoInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
