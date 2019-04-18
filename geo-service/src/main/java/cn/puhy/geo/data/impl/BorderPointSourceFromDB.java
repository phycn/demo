package cn.puhy.geo.data.impl;

import cn.puhy.geo.DBUtil;
import cn.puhy.geo.data.BorderPointSource;
import cn.puhy.geo.model.GeoInfo;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component("borderPointSourceFromDB")
public class BorderPointSourceFromDB implements BorderPointSource {
    @Override
    public Map<String, GeoInfo> generateGeoInfoMap() {
        Connection connection = DBUtil.getConn();
        String sql1 = "select distinct name from geo";
        Set<String> cityNames = new HashSet<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql1);
            ResultSet resultSet1 = pstmt.executeQuery();
            while (resultSet1.next()) {
                String name = resultSet1.getString(1);
                cityNames.add(name);
            }
            String sql2 = "select lon, lat from geo where name = ?";
            pstmt = connection.prepareStatement(sql2);

            Map<String, GeoInfo> map = new HashMap<>();
            for (String cityName : cityNames) {

                pstmt.setString(1, cityName);
                ResultSet resultSet2 = pstmt.executeQuery();

                List<Double> lons = new LinkedList<>();
                List<Double> lats = new LinkedList<>();
                while (resultSet2.next()) {
                    lons.add(Double.parseDouble(resultSet2.getString(1)));
                    lats.add(Double.parseDouble(resultSet2.getString(2)));
                }
                int length = lons.size();
                double[] borderLons = new double[length];
                double[] borderLats = new double[length];
                for (int i = 0; i < length; i++) {
                    borderLons[i] = lons.get(i);
                    borderLats[i] = lats.get(i);
                }

                GeoInfo geoInfo = new GeoInfo();
                geoInfo.setCode(cityName);
                geoInfo.setName(cityName);
                geoInfo.setBorderLons(borderLons);
                geoInfo.setBorderLats(borderLats);
                geoInfo.setMaxLon(Arrays.stream(borderLons).max().orElse(0));
                geoInfo.setMinLon(Arrays.stream(borderLons).min().orElse(0));
                geoInfo.setMaxLat(Arrays.stream(borderLats).max().orElse(0));
                geoInfo.setMinLat(Arrays.stream(borderLats).min().orElse(0));
                System.out.println(geoInfo);
                map.put(cityName, geoInfo);
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
