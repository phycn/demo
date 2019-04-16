package cn.puhy.demo.geo.algorithm.data;

import cn.puhy.demo.geo.algorithm.GeoSearchNode;

public interface GeoSearchService {
    GeoSearchNode getNode(double lon, double lat);
}
