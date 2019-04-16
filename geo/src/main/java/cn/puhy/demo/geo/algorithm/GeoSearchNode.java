package cn.puhy.demo.geo.algorithm;

public class GeoSearchNode implements Comparable<GeoSearchNode> {
    private String code;
    private int pointOrSection;
    private int lonOrLat;
    private int maxOrMin;
    private double value;
    private String[] codeRange;

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

    public String[] getCodeRange() {
        return codeRange;
    }

    public void setCodeRange(String[] codeRange) {
        this.codeRange = codeRange;
    }

    @Override
    public int compareTo(GeoSearchNode geoSearchNode) {
        return this.value > geoSearchNode.getValue() ? 1 : 0;
    }
}
