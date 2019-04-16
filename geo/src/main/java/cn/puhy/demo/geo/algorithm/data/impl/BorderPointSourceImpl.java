package cn.puhy.demo.geo.algorithm.data.impl;

import cn.puhy.demo.geo.algorithm.GeoInfo;
import cn.puhy.demo.geo.algorithm.data.BorderPointSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class BorderPointSourceImpl implements BorderPointSource {

    private String dirPath = "C:\\Users\\puhongyu\\Desktop\\geo";

    @Override
    public GeoInfo[] generateGeoInfos() {
        File dirFile = new File(dirPath);
        File[] files = dirFile.listFiles();
        GeoInfo[] geoInfos = new GeoInfo[files.length];
        for (int i = 0; i < files.length; i++) {
            GeoInfo geoInfo = generateGeoInfo(files[i].getAbsolutePath());
            geoInfos[i] = geoInfo;
        }
        return geoInfos;
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
        geoInfo.setMinLon(Arrays.stream(lats).min().orElse(0));
        geoInfo.setName(Paths.get(filePath).getFileName().toString());
        return geoInfo;
    }
}
