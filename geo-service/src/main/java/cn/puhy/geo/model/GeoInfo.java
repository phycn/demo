package cn.puhy.geo.model;

import java.util.List;

public class GeoInfo extends GeoBase {

    private double maxLon;          // 最大经度
    private double minLon;          // 最小经度
    private double maxLat;          // 最大纬度
    private double minLat;          // 最小纬度
    private List<double[]> borderLons;
    private List<double[]> borderLats;

    public double getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(double maxLon) {
        this.maxLon = maxLon;
    }

    public double getMinLon() {
        return minLon;
    }

    public void setMinLon(double minLon) {
        this.minLon = minLon;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }


    @Override
    public String toString() {
        return "GeoInfo{" +
                "name='" + getName() + '\'' +
                ", code='" + getCode() + '\'' +
                ", maxLon=" + maxLon +
                ", minLon=" + minLon +
                ", maxLat=" + maxLat +
                ", minLat=" + minLat +
                ", borderLons.length=" + borderLons.size() +
                ", borderLats.length=" + borderLats.size() +
                '}';
    }

    public List<double[]> getBorderLons() {
        return borderLons;
    }

    public void setBorderLons(List<double[]> borderLons) {
        this.borderLons = borderLons;
    }

    public List<double[]> getBorderLats() {
        return borderLats;
    }

    public void setBorderLats(List<double[]> borderLats) {
        this.borderLats = borderLats;
    }
}
