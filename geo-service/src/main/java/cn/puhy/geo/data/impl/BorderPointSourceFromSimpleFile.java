package cn.puhy.geo.data.impl;

import cn.puhy.geo.data.BorderPointSource;
import cn.puhy.geo.model.GeoInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author puhongyu
 * 2019/4/19 08:55
 */
@Component("borderPointSourceFromSimpleFile")
public class BorderPointSourceFromSimpleFile implements BorderPointSource {

    @Value("${geo.simple.filepath}")
    private String filePath;

    @Override
    public Map<String, GeoInfo> generateGeoInfoMap() {
        Map<String, GeoInfo> map = new HashMap<>();
        try {
            Resource resource = new ClassPathResource(filePath);
            InputStream inputStream = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] point = line.split("\\|");
                String name = point[0];
                String code = point[1];
                int length = point.length;

                List<double[]> borderLonList = new LinkedList<>();
                List<double[]> borderLatList = new LinkedList<>();

                for (int i = 2; i < length; i++) {
                    String[] blockPoints = point[i].split(";");
                    int blockPointsLength = blockPoints.length;

                    double[] borderLons = new double[blockPointsLength];
                    double[] borderLats = new double[blockPointsLength];

                    for (int j = 0; j < blockPointsLength; j++) {
                        String[] lonLat = blockPoints[j].split(",");
                        borderLons[j] = Double.parseDouble(lonLat[0]);
                        borderLats[j] = Double.parseDouble(lonLat[1]);
                    }
                    borderLonList.add(borderLons);
                    borderLatList.add(borderLats);
                }

                GeoInfo geoInfo = new GeoInfo();
                geoInfo.setCode(code);
                geoInfo.setName(name);
                geoInfo.setBorderLons(borderLonList);
                geoInfo.setBorderLats(borderLatList);
                geoInfo.setMaxLon(borderLonList.stream().flatMapToDouble(Arrays::stream).max().orElse(0));
                geoInfo.setMinLon(borderLonList.stream().flatMapToDouble(Arrays::stream).min().orElse(0));
                geoInfo.setMaxLat(borderLatList.stream().flatMapToDouble(Arrays::stream).max().orElse(0));
                geoInfo.setMinLat(borderLatList.stream().flatMapToDouble(Arrays::stream).min().orElse(0));

                map.put(code, geoInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
