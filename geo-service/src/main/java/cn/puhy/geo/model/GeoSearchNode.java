package cn.puhy.geo.model;

import java.util.List;

public class GeoSearchNode extends GeoBase implements Comparable<GeoSearchNode> {

    private int pointOrSection;     // 点或者区间
    private int lonOrLat;           // 经度或纬度
    private int maxOrMin;           // 最大值或最小值
    private double value;           // 经度值或纬度值
    private List<String> codeRange;

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
                "code='" + getCode() + '\'' +
                ", pointOrSection=" + pointOrSection +
                ", lonOrLat=" + lonOrLat +
                ", maxOrMin=" + maxOrMin +
                ", value=" + value +
                ", codeRange=" + codeRange +
                '}';
    }
}
