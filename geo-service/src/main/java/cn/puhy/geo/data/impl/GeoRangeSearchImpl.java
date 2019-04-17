package cn.puhy.geo.data.impl;

import cn.puhy.geo.constant.GeoConstants;
import cn.puhy.geo.data.GeoRangeSearch;
import cn.puhy.geo.model.GeoInfo;
import cn.puhy.geo.model.GeoSearchNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeoRangeSearchImpl implements GeoRangeSearch {

    /**
     * 构造
     * @param geoInfos
     * @param lonOrLat
     * @return
     */
    @Override
    public List<GeoSearchNode> generateGeoSearchNodes(List<GeoInfo> geoInfos, int lonOrLat) {
        List<GeoSearchNode> temp = new ArrayList<>();
        for (GeoInfo geoInfo : geoInfos) {
            GeoSearchNode maxGeoSearchNode = new GeoSearchNode();
            GeoSearchNode minGeoSearchNode = new GeoSearchNode();
            double max;
            double min;
            if (lonOrLat == GeoConstants.LON) {
                max = geoInfo.getMaxLon();
                min = geoInfo.getMinLon();
            } else {
                max = geoInfo.getMaxLat();
                min = geoInfo.getMinLat();
            }
            maxGeoSearchNode.setCode(geoInfo.getCode());
            maxGeoSearchNode.setLonOrLat(lonOrLat);
            maxGeoSearchNode.setMaxOrMin(GeoConstants.MAX);
            maxGeoSearchNode.setPointOrSection(GeoConstants.TYPE_POINT);
            maxGeoSearchNode.setValue(max);

            minGeoSearchNode.setCode(geoInfo.getCode());
            minGeoSearchNode.setLonOrLat(lonOrLat);
            minGeoSearchNode.setMaxOrMin(GeoConstants.MIN);
            minGeoSearchNode.setPointOrSection(GeoConstants.TYPE_POINT);
            minGeoSearchNode.setValue(min);

            temp.add(maxGeoSearchNode);
            temp.add(minGeoSearchNode);
        }
        // 点排序
        temp = temp.stream().sorted().collect(Collectors.toList());
        List<GeoSearchNode> geoSearchNodes = new ArrayList<>();
        // 区间
        List<String> section = new ArrayList<>();
        for (int i = 0, length = temp.size(); i < length; i++) {
            GeoSearchNode pointNode = temp.get(i);
            geoSearchNodes.add(pointNode);
            // 所有点最大的值后没有区间
            if (i < length - 1) {
                GeoSearchNode sectionNode = new GeoSearchNode();
                if (pointNode.getMaxOrMin() == GeoConstants.MIN) {
                    section.add(pointNode.getCode());
                } else {
                    section.remove(pointNode.getCode());
                }
                List<String> cloneSection = new ArrayList<>(section);
                sectionNode.setCodeRange(cloneSection);
                sectionNode.setPointOrSection(GeoConstants.TYPE_SECTION);
                sectionNode.setLonOrLat(pointNode.getLonOrLat());
                geoSearchNodes.add(sectionNode);
            }
        }
        return geoSearchNodes;
    }
}
