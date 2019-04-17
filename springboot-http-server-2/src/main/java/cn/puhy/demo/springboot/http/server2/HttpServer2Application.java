package cn.puhy.demo.springboot.http.server2;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author PUHY
 * 2019-03-30 18:12
 */
@SpringBootApplication
@EnableDubboConfiguration
public class HttpServer2Application {
    public static void main(String[] args) {
        SpringApplication.run(HttpServer2Application.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
