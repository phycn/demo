package cn.puhy.geo.gaode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


@Service
public class RequestGaode {

    @Autowired
    private RestTemplate restTemplate;

    private List<String> newLines = new LinkedList<>();

    public void district(String keywords, String code) {

        String url = "https://restapi.amap.com/v3/config/district?key=" +
                "e134d898bd7d01696120f2adf3aef018&subdistrict=0&extensions=all&keywords=" + keywords + "&filter=" + code;
        String response = restTemplate.getForEntity(url, String.class).getBody();
        JSONObject jsonObject = JSONObject.parseObject(response);
        String status = jsonObject.getString("status");
        if ("1".equals(status)) {
            JSONArray districts = jsonObject.getJSONArray("districts");
            if (districts.size() > 0) {
                JSONObject district = districts.getJSONObject(0);
                newLines.add(keywords + "|" + code + "|" + district.getString("polyline"));
            } else {
                System.out.println(keywords + "|" + code);
            }
        }
    }

    public void district1() {
        Path path = Paths.get("C:\\Users\\puhongyu\\Desktop\\遗漏城市.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] array = line.split("\\|");
                if (array.length != 2) {
                    continue;
                }
                district(array[0], array[1]);
            }
            Files.write(Paths.get("C:\\Users\\puhongyu\\Desktop\\geo-simple-20190529.txt"), newLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
