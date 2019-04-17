package cn.puhy.geo.strategy;

import cn.puhy.geo.algorithm.InRegionAlgorithm;
import cn.puhy.geo.model.GeoInfo;
import cn.puhy.geo.model.GeoSearchNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PointSectionGeoStrategy implements GeoStrategy {

    @Autowired
    @Qualifier("lonNodes")
    private List<GeoSearchNode> lonNodes;

    @Autowired
    @Qualifier("latNodes")
    private List<GeoSearchNode> latNodes;

    @Autowired
    @Qualifier("geoInfoMap")
    private Map<String, GeoInfo> geoInfoMap;

    @Autowired
    private InRegionAlgorithm inRegionAlgorithm;

    @Override
    public String getCode(double lon, double lat) {
        List<String> lonRange = determineRange(lonNodes, lon);
        List<String> latRange = determineRange(latNodes, lat);
        // 两个范围的交集
        lonRange.retainAll(latRange);
        // 交集为空说明不在已知城市内
        if (lonRange.size() == 0) {
            return "";
        }
        String cityCode = "";
        for (String code : lonRange) {
            GeoInfo geoInfo = geoInfoMap.get(code);
            boolean inRegion = inRegionAlgorithm.inRegion(geoInfo.getBorderLons(), geoInfo.getBorderLats(), lon, lat);
            if (inRegion) {
                cityCode = code;
                break;
            }
        }
        return cityCode;
    }

    /**
     * 确定经度或纬度会在哪些城市内，目前采用遍历数组方式，后续可以优化为使用AVL树
     *
     * @param nodes
     * @param value
     * @return
     */
    private List<String> determineRange(List<GeoSearchNode> nodes, double value) {
        List<String> range = new ArrayList<>();
        // 索引为偶数的是点，奇数是区间
        for (int i = 0, length = nodes.size(); i < length; i += 2) {
            GeoSearchNode node = nodes.get(i);
            // 相等说明是在边界点上
            if (value == node.getValue()) {
                range.add(node.getCode());
                break;
            }
            if (value < node.getValue()) {
                // 小于最小值说明不在范围内
                if (i == 0) {
                    break;
                }
                range = nodes.get(i - 1).getCodeRange();
                break;
            }
            // 大于最大值说明不在范围内
            if (value > node.getValue() && i == length - 1) {
                break;
            }
        }
        return range;
    }
}