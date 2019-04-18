package cn.puhy.geo.data.impl;

import cn.puhy.geo.model.GeoInfo;
import cn.puhy.geo.data.BorderPointSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("borderPointSourceFromMultiFile")
public class BorderPointSourceFromMultiFile implements BorderPointSource {

    private String dirPath = "C:\\Users\\puhongyu\\Desktop\\geo";

    @Override
    public Map<String, GeoInfo> generateGeoInfoMap() {
        File dirFile = new File(dirPath);
        File[] files = dirFile.listFiles();
        Map<String, GeoInfo> map = new HashMap<>();
        for (File file : files) {
            GeoInfo geoInfo = generateGeoInfo(file.getAbsolutePath());
            map.put(file.getName(), geoInfo);
        }
        return map;
    }

    private GeoInfo generateGeoInfo(String filePath) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lonLats = lines.get(0).split(";");
        int length = lonLats.length;
        double[] lons = new double[length];
        double[] lats = new double[length];

        GeoInfo geoInfo = new GeoInfo();
        for (int i = 0; i < length; i++) {
            String[] lonLat = lonLats[i].split(",");
            double lon = Double.parseDouble(lonLat[0]);
            double lat = Double.parseDouble(lonLat[1]);

            lons[i] = lon;
            lats[i] = lat;
        }
        geoInfo.setBorderLons(lons);
        geoInfo.setBorderLats(lats);

        geoInfo.setMaxLon(Arrays.stream(lons).max().orElse(0));
        geoInfo.setMinLon(Arrays.stream(lons).min().orElse(0));
        geoInfo.setMaxLat(Arrays.stream(lats).max().orElse(0));
        geoInfo.setMinLat(Arrays.stream(lats).min().orElse(0));
        geoInfo.setName(Paths.get(filePath).getFileName().toString());
        geoInfo.setCode(Paths.get(filePath).getFileName().toString());
        return geoInfo;
    }
}
