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
        temp = temp.stream().sorted().collect(Collectors.toList());
        List<GeoSearchNode> geoSearchNodes = new ArrayList<>();
        for (int i = 1, length = temp.size(); i < length; i++) {

        }

        return temp;
    }
}
