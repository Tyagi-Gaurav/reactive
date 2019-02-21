package org.gt.shipping.cargo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableCircuitBreaker
@Slf4j
public class CargoServiceApplication {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        log.info("Creating load balanced rest client");
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(CargoServiceApplication.class, args);
    }
}
