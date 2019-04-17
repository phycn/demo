package cn.puhy.geo.algorithm;

public interface InRegionAlgorithm {
    boolean inRegion(double[] lons, double[] lats, double lon, double lat);
}
