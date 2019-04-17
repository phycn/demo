package cn.puhy.demo.springboot.http.server2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author PUHY
 * 2019-03-30 22:11
 */
@RestController
public class HttpServer2Controller {

    private static Logger logger = LoggerFactory.getLogger(HttpServer2Controller.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/method1")
    public void method1() {
        logger.info("http server 2 ");
    }

    @GetMapping("/method2")
    public void method2() {
        restTemplate.getForEntity("http://localhost:8080/method1", Void.class);
        logger.info("http server 2 ");
    }
}
