package org.gt.shipping.carrier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@RefreshScope
@EnableResourceServer
public class CarrierSimulatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarrierSimulatorApplication.class, args);
    }
}
