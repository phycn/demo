package cn.puhy.geo.controller;

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

    @GetMapping("/geo/{lon}/{lat}")
    public GeoBase getCityCode(@PathVariable("lon") double lon, @PathVariable("lat") double lat) {
        long start = System.currentTimeMillis();
        GeoBase geoBase = geoService.getCode(lon, lat);
        logger.info("判断耗时: " + (System.currentTimeMillis() - start));
        return geoBase;
    }
}
