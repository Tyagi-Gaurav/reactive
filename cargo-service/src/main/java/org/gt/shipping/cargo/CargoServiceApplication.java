package org.gt.shipping.cargo;

import lombok.extern.slf4j.Slf4j;
import org.gt.shipping.cargo.filter.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableResourceServer //Tells that this is a protective resource
@Slf4j
public class CargoServiceApplication {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        log.info("Creating load balanced rest client");
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        UserContextInterceptor userContextInterceptor = new UserContextInterceptor();

        if (interceptors != null) {
            interceptors.add(userContextInterceptor);
            restTemplate.setInterceptors(interceptors);
        } else {
            restTemplate.setInterceptors(Collections.singletonList(userContextInterceptor));
        }

        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(CargoServiceApplication.class, args);
    }
}
