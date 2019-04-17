package cn.puhy.geo.data;

import cn.puhy.geo.constant.GeoConstants;
import cn.puhy.geo.model.GeoInfo;
import cn.puhy.geo.model.GeoSearchNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GeoDataHandler {

    @Autowired
    private BorderPointSource borderPointSource;

    @Autowired
    private GeoRangeSearch geoRangeSearch;

    @Bean("geoInfoMap")
    public Map<String, GeoInfo> geoInfoMap() {
        return borderPointSource.generateGeoInfoMap();
    }

    @Bean("lonNodes")
    public List<GeoSearchNode> lonNodes(Map<String, GeoInfo> geoInfoMap) {
        List<GeoInfo> list = new ArrayList<>(geoInfoMap.values());
        return geoRangeSearch.generateGeoSearchNodes(list, GeoConstants.LON);
    }

    @Bean("latNodes")
    public List<GeoSearchNode> latNodes(Map<String, GeoInfo> geoInfoMap) {
        List<GeoInfo> list = new ArrayList<>(geoInfoMap.values());
        return geoRangeSearch.generateGeoSearchNodes(list, GeoConstants.LAT);
    }
}
