package cn.puhy.geo.gaode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        // StringHttpMessageConverter默认编码为ISO_8859_1，需要自定义初始化
        StringHttpMessageConverter stringHttpMessageConverter =
                new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>(2);
        messageConverters.add(stringHttpMessageConverter);
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(10000);
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }
}
