package org.gt.shipping;

import org.gt.shipping.service.security.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
public class TestConfiguration {
    @Bean
    public SecurityService securityService() { return new SecurityService();}

    @Bean
    public Client client() {
        return ClientBuilder.newClient();
    }
}
