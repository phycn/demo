package cn.puhy.geo.data;

import cn.puhy.geo.model.GeoInfo;
import cn.puhy.geo.model.GeoSearchNode;

import java.util.List;

public interface GeoRangeSearch {
    List<GeoSearchNode> generateGeoSearchNodes(List<GeoInfo> geoInfos, int lonOrLat);
}
