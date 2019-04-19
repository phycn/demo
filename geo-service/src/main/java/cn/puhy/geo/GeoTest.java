package cn.puhy.geo;

import cn.puhy.geo.model.GeoSearchNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeoTest {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("1");
        list1.add("2");

        list2.add("2");
        list2.add("3");
        
        list1.retainAll(list2);
        System.out.println(list1);
    }
}
