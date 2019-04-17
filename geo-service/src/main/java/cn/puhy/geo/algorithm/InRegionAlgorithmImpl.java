package cn.puhy.geo.algorithm;

import org.springframework.stereotype.Component;

@Component
public class InRegionAlgorithmImpl implements InRegionAlgorithm {

    @Override
    public boolean inRegion(double[] lons, double[] lats, double lon, double lat) {
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
