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
    @Qualifier("borderPointSourceFromSingleFile")
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

    @Bean("lonNodes")
    public List<GeoSearchNode> lonNodes(Map<String, GeoInfo> geoInfoMap) {
        List<GeoInfo> list = new ArrayList<>(geoInfoMap.values());
        System.out.println("经度范围搜索初始化开始...");
        long start = System.currentTimeMillis();
        List<GeoSearchNode> geoSearchNodeList = geoRangeSearch.generateGeoSearchNodes(list, GeoConstants.LON);
        System.out.println("经度范围搜索初始化完成, 耗时: " + (System.currentTimeMillis() - start));
        return geoSearchNodeList;
    }

    @Bean("latNodes")
    public List<GeoSearchNode> latNodes(Map<String, GeoInfo> geoInfoMap) {
        List<GeoInfo> list = new ArrayList<>(geoInfoMap.values());
        System.out.println("经度范围搜索初始化开始...");
        long start = System.currentTimeMillis();
        List<GeoSearchNode> geoSearchNodeList = geoRangeSearch.generateGeoSearchNodes(list, GeoConstants.LAT);
        System.out.println("经度范围搜索初始化完成, 耗时: " + (System.currentTimeMillis() - start));
        return geoSearchNodeList;
    }
}
