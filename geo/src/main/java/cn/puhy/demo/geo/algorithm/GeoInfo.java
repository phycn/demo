package cn.puhy.demo.geo.algorithm;

import java.util.Arrays;

public class GeoInfo {

    private String name;
    private String code;
    private double maxLon;
    private double minLon;
    private double maxLat;
    private double minLat;
    private double[] borderLons;
    private double[] borderLats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public double[] getBorderLons() {
        return borderLons;
    }

    public void setBorderLons(double[] borderLons) {
        this.borderLons = borderLons;
    }

    public double[] getBorderLats() {
        return borderLats;
    }

    public void setBorderLats(double[] borderLats) {
        this.borderLats = borderLats;
    }

    @Override
    public String toString() {
        return "GeoInfo{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", maxLon=" + maxLon +
                ", minLon=" + minLon +
                ", maxLat=" + maxLat +
                ", minLat=" + minLat +
                ", borderLons.length=" + borderLons.length +
                ", borderLats.length=" + borderLats.length +
                '}';
    }
}
