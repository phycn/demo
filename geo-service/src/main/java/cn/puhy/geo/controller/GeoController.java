package cn.puhy.geo.controller;

import cn.puhy.geo.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoController {

    @Autowired
    private GeoService geoService;

    @GetMapping("/geo/{lon}/{lat}")
    public String getCityCode(@PathVariable("lon") double lon, @PathVariable("lat") double lat) {
        return geoService.getCode(lon, lat);
    }
}
