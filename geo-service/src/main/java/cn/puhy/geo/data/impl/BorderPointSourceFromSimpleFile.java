package cn.puhy.geo.data.impl;

import cn.puhy.geo.data.BorderPointSource;
import cn.puhy.geo.model.GeoInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<String> lines = Files.readAllLines(Paths.get(resource.getFile().getAbsolutePath()));
            for (String line : lines) {
                String[] point = line.split("\\|");
                String name = point[0];
                String code = point[1];
                int length = point.length;
                double[] borderLons = new double[length - 2];
                double[] borderLats = new double[length - 2];
                for (int i = 2; i < length; i++) {
                    String[] lonLat = point[i].split(",");
                    borderLons[i - 2] = Double.parseDouble(lonLat[0]);
                    borderLats[i - 2] = Double.parseDouble(lonLat[1]);
                }
                GeoInfo geoInfo = new GeoInfo();
                geoInfo.setCode(code);
                geoInfo.setName(name);
                geoInfo.setBorderLons(borderLons);
                geoInfo.setBorderLats(borderLats);
                geoInfo.setMaxLon(Arrays.stream(borderLons).max().orElse(0));
                geoInfo.setMinLon(Arrays.stream(borderLons).min().orElse(0));
                geoInfo.setMaxLat(Arrays.stream(borderLats).max().orElse(0));
                geoInfo.setMinLat(Arrays.stream(borderLats).min().orElse(0));

                map.put(code, geoInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
