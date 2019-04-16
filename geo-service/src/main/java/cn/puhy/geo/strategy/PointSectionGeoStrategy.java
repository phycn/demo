package cn.puhy.geo.strategy;

import cn.puhy.geo.data.BorderPointSource;
import cn.puhy.geo.data.GeoRangeSearch;
import cn.puhy.geo.model.GeoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PointSectionGeoStrategy implements GeoStrategy {

    private GeoInfo[] geoInfos;

    @Autowired
    private BorderPointSource borderPointSource;

    @Autowired
    private GeoRangeSearch geoRangeSearch;

    @PostConstruct
    public void init() {
        geoInfos = borderPointSource.generateGeoInfos();
    }

    @Override
    public String getCode(double lon, double lat) {

        return null;
    }
}
