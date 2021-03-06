package cn.puhy.geo.controller;

import cn.puhy.geo.gaode.Regeo;
import cn.puhy.geo.gaode.RequestGaode;
import cn.puhy.geo.model.GeoBase;
import cn.puhy.geo.service.GeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoController {

    private static Logger logger = LoggerFactory.getLogger(GeoController.class);

    @Autowired
    private GeoService geoService;

    @Autowired
    private RequestGaode requestGaode;

    @Autowired
    private Regeo regeo;

    @GetMapping("/geo/{lon}/{lat}")
    public GeoBase getCityCode(@PathVariable("lon") double lon, @PathVariable("lat") double lat) {
        long start = System.currentTimeMillis();
        GeoBase geoBase = geoService.getCode(lon, lat);
        logger.info("判断耗时: " + (System.currentTimeMillis() - start));
        return geoBase;
    }

    @GetMapping("/geo1/{lonlat}")
    public GeoBase getCityCode1(@PathVariable("lonlat") String lonlat) {
        double lon = Double.parseDouble(lonlat.split(",")[1]);
        double lat = Double.parseDouble(lonlat.split(",")[0]);
        long start = System.currentTimeMillis();
        GeoBase geoBase = geoService.getCode(lon, lat);
        logger.info("判断耗时: " + (System.currentTimeMillis() - start));
        return geoBase;
    }

    @GetMapping("/gaode")
    public void gaode() {
        requestGaode.district1();
    }

    @GetMapping("/regeo1")
    public void regeo1() {
        regeo.regeo1();
    }

    @GetMapping("/regeo/{lonlat}")
    public String regeo(@PathVariable("lonlat") String lonlat) {
        String lon = lonlat.split(",")[1];
        String lat = lonlat.split(",")[0];
        return regeo.regeo(lon, lat);
    }
}
