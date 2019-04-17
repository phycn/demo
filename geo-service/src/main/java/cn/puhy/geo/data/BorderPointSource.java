package cn.puhy.geo.data;

import cn.puhy.geo.model.GeoInfo;

import java.util.Map;

public interface BorderPointSource {
    Map<String, GeoInfo> generateGeoInfoMap();
}
