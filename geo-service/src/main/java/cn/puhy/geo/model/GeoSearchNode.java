package cn.puhy.geo.model;

import java.util.List;

public class GeoSearchNode implements Comparable<GeoSearchNode> {
    private String code;
    private int pointOrSection;
    private int lonOrLat;
    private int maxOrMin;
    private double value;
    private List<String> codeRange;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPointOrSection() {
        return pointOrSection;
    }

    public void setPointOrSection(int pointOrSection) {
        this.pointOrSection = pointOrSection;
    }

    public int getLonOrLat() {
        return lonOrLat;
    }

    public void setLonOrLat(int lonOrLat) {
        this.lonOrLat = lonOrLat;
    }

    public int getMaxOrMin() {
        return maxOrMin;
    }

    public void setMaxOrMin(int maxOrMin) {
        this.maxOrMin = maxOrMin;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<String> getCodeRange() {
        return codeRange;
    }

    public void setCodeRange(List<String> codeRange) {
        this.codeRange = codeRange;
    }

    @Override
    public int compareTo(GeoSearchNode geoSearchNode) {
        return Double.compare(this.value, geoSearchNode.getValue());
    }

    @Override
    public String toString() {
        return "GeoSearchNode{" +
                "code='" + code + '\'' +
                ", pointOrSection=" + pointOrSection +
                ", lonOrLat=" + lonOrLat +
                ", maxOrMin=" + maxOrMin +
                ", value=" + value +
                ", codeRange=" + codeRange +
                '}';
    }
}
