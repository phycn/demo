package cn.puhy.demo.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author PUHY
 * 2019-03-31 22:06
 */
@SpringBootApplication
@EnableDubboConfiguration
public class DubboServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboServerApplication.class, args);
    }
}
