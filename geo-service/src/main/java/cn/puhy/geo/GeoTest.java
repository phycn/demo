package cn.puhy.geo;

import cn.puhy.geo.model.GeoSearchNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeoTest {
    public static void main(String[] args) {
        GeoSearchNode geoSearchNode1 = new GeoSearchNode();
        geoSearchNode1.setValue(1.1);

        GeoSearchNode geoSearchNode2 = new GeoSearchNode();
        geoSearchNode2.setValue(2.3);

        GeoSearchNode geoSearchNode3 = new GeoSearchNode();
        geoSearchNode3.setValue(0.3);

        List<GeoSearchNode> list = new ArrayList<>();
        list.add(geoSearchNode1);
        list.add(geoSearchNode2);
        list.add(geoSearchNode3);

        list.sort(GeoSearchNode::compareTo);

//        list = list.stream().sorted(GeoSearchNode::compareTo).collect(Collectors.toList());

        System.out.println(list);
    }
}
