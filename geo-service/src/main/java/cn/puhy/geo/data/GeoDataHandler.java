package cn.puhy.geo.data;

import cn.puhy.geo.constant.GeoConstants;
import cn.puhy.geo.model.GeoInfo;
import cn.puhy.geo.model.GeoSearchNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GeoDataHandler {

    @Autowired
    @Qualifier("borderPointSourceFromSimpleFile")
    private BorderPointSource borderPointSource;

    @Autowired
    private GeoRangeSearch geoRangeSearch;

    @Bean("geoInfoMap")
    public Map<String, GeoInfo> geoInfoMap() {
        System.out.println("地图边界点信息加载开始...");
        long start = System.currentTimeMillis();
        Map<String, GeoInfo> map = borderPointSource.generateGeoInfoMap();
        System.out.println("地图边界点信息加载完成, 耗时: " + (System.currentTimeMillis() - start));
        return map;
    }

    @Bean("geoInfos")
    public List<GeoInfo> geoInfos(Map<String, GeoInfo> geoInfoMap) {
        return new ArrayList<>(geoInfoMap.values());
    }

    @Bean("lonNodes")
    public List<GeoSearchNode> lonNodes(List<GeoInfo> geoInfos) {
        return geoRangeSearch.generateGeoSearchNodes(geoInfos, GeoConstants.LON);
    }

    @Bean("latNodes")
    public List<GeoSearchNode> latNodes(List<GeoInfo> geoInfos) {
        return geoRangeSearch.generateGeoSearchNodes(geoInfos, GeoConstants.LAT);
    }
}
