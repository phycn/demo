package cn.puhy.geo.strategy;

import cn.puhy.geo.model.GeoBase;

public interface GeoStrategy {
    GeoBase getCode(double lon, double lat);
}
