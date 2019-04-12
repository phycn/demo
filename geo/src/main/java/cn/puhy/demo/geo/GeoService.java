package cn.puhy.demo.geo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoService {
    private List<GeoInfo> geoList = new ArrayList<>();
    private Map<String, GeoInfo> geoMap = new HashMap<>();

    private void init() {

    }

    public boolean isIn(double lon, double lat) {
        return false;
    }

    private boolean judge(double[] lons, double[] lats, double lon, double lat) {
        int count = lons.length;
        for (int i = 0, j = count - 1; i < count; j = i++) {
            if (((lats[i] > lat) != (lats[j] > lat)) &&
                    (lon < (lons[j] - lons[i]) * (lat - lats[i]) / (lats[j] - lats[i]) + lons[i])) {
                return true;
            }
        }
        return false;
    }
}
