package cn.puhy.geo.controller;

import cn.puhy.geo.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class GeoController {

    @Autowired
    private GeoService geoService;

    @GetMapping("/geo/{lon}/{lat}")
    public String getCityCode(@PathVariable("lon") double lon, @PathVariable("lat") double lat) {
        long start = System.currentTimeMillis();
        String code = geoService.getCode(lon, lat);
        System.out.println(System.currentTimeMillis() - start);
        return code;
    }

    @GetMapping("/geo1")
    public void multiThread() throws InterruptedException {
        double[] lons = {126.5358, 104.77844, 114.30525, 126.96932, 97.1783638};
        double[] lats = {45.80216, 29.3392, 30.59276, 46.65246, 31.1413499049};
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int n = random.nextInt(lons.length);
                    long start = System.currentTimeMillis();
                    String code = geoService.getCode(lons[n], lats[n]);
                    System.out.println(code + " 耗时: " + (System.currentTimeMillis() - start));
                }
            });
            thread.start();
//            thread.join();
        }
    }
}
