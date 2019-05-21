package cn.puhy.demo.springboot.http.server1.controller;

import cn.puhy.demo.dubbo.api.DemoService;
import cn.puhy.demo.dubbo.api.EnumTestService;
import cn.puhy.demo.dubbo.api.MyEnum;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PUHY
 * 2019-03-30 22:11
 */
@RestController
public class HttpServerController {

    private static Logger logger = LoggerFactory.getLogger(HttpServerController.class);

    @Reference(check = false)
    private DemoService demoService;

    @Reference(check = false)
    private EnumTestService enumTestService;

    @GetMapping("/method1")
    public void method1() {
        demoService.service1();
        demoService.service2();
        enumTestService.myEnumTest(MyEnum.Enum1);
        logger.info(MDC.get("X-B3-TraceId"));
        logger.info("http server 1 ");
    }
}
