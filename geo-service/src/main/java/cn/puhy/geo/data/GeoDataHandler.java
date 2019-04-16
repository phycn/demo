package cn.puhy.geo.data;

import cn.puhy.geo.model.GeoInfo;
import cn.puhy.geo.model.GeoSearchNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GeoDataHandler {

    private GeoInfo[] geoInfos;
    private GeoSearchNode[] geoSearchNodes;

    @Autowired
    private BorderPointSource borderPointSource;

    @PostConstruct
    public void init() {
        geoInfos = borderPointSource.generateGeoInfos();
    }
}
