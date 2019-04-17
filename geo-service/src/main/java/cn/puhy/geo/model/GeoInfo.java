package cn.puhy.geo.model;

public class GeoInfo {

    private String name;            // 城市名称
    private String code;            // 城市编码
    private double maxLon;          // 最大经度
    private double minLon;          // 最小经度
    private double maxLat;          // 最大纬度
    private double minLat;          // 最小纬度
    private double[] borderLons;    // 经度边界值
    private double[] borderLats;    // 纬度边界值

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