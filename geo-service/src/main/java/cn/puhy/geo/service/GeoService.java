package cn.puhy.geo.service;

import cn.puhy.geo.model.GeoBase;
import cn.puhy.geo.strategy.GeoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

    @Autowired
    private GeoStrategy geoStrategy;

    public GeoBase getCode(double lon, double lat) {
        return geoStrategy.getCode(lon, lat);
    }
}
