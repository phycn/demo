package cn.puhy.geo.gaode;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class Regeo {

    @Autowired
    private RestTemplate restTemplate;

    public String regeo(String lon, String lat) {
        String location = lon + "," + lat;
        String radius = "1000";
        String url = "https://restapi.amap.com/v3/geocode/regeo?key=" +
                "e134d898bd7d01696120f2adf3aef018&location=" + location + "&redius=" + radius;
        String response = restTemplate.getForEntity(url, String.class).getBody();
        System.out.println(response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        String code = jsonObject.getJSONObject("regeocode").getJSONObject("addressComponent").getString("adcode");
        return response;
    }

    public void regeo1() {
        Path path = Paths.get("C:\\Users\\puhongyu\\Desktop\\tmp\\simple.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] array = line.split("\\|");
                String lonLat = array[0];
                String gaodeCode = regeo(lonLat.split(",")[1], lonLat.split(",")[0]);
                String geoCode = "";
                if (array.length == 3) {
                    geoCode = array[2];
                }
                if (!gaodeCode.equals(geoCode)) {
                    System.out.println(line);
                }
//                System.out.println(gaodeCode + " " + geoCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
