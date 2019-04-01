package cn.puhy.demo.geo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author PUHY
 * 2019-04-01 21:41
 */
public class GeoToDB {
    public static void main(String[] args) throws IOException, SQLException {
        List<String> list = Files.readAllLines(Paths.get("C:\\Users\\PUHY\\Desktop\\geo.txt"));
        JSONArray jsonArray = JSONArray.parseArray(list.get(0));

        Connection connection = getConn();
        String sql = "insert into geo (name,p,r,lng,lat) values(?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray array = jsonArray.getJSONObject(i).getJSONArray("geo");
            String name = jsonArray.getJSONObject(i).getString("name");
            System.out.println(array);
            for (int j = 0; j < array.size(); j++) {

                JSONObject jsonObject = array.getJSONObject(j);

                String p = jsonObject.getString("P");
                String r = jsonObject.getString("R");
                String lng = jsonObject.getString("lng");
                String lat = jsonObject.getString("lat");

                pstmt.setString(1, name);
                pstmt.setString(2, p);
                pstmt.setString(3, r);
                pstmt.setString(4, lng);
                pstmt.setString(5, lat);

                pstmt.executeUpdate();

            }
        }
        pstmt.close();
        connection.close();
    }

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://119.27.174.130:3306/phy?characterEncoding=utf8";
        String username = "root";
        String password = "253399933";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
