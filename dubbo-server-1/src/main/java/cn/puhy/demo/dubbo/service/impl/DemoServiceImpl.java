package cn.puhy.demo.dubbo.service.impl;

import cn.puhy.demo.dubbo.api.DemoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author PUHY
 * 2019-03-31 21:26
 */
@Service(interfaceClass = DemoService.class)
@Component
public class DemoServiceImpl implements DemoService {

    private Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public void service1() {
        logger.info("dubbo server 1 service1");
    }
}
