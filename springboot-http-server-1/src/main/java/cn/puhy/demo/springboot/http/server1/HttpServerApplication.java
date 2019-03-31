package cn.puhy.demo.springboot.http.server1;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author PUHY
 * 2019-03-30 18:12
 */
@SpringBootApplication
@EnableDubboConfiguration
public class HttpServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpServerApplication.class);
    }
}
