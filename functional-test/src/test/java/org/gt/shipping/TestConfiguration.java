package org.gt.shipping;

import org.gt.shipping.service.cargo.TestCargoService;
import org.gt.shipping.service.kafka.KafkaClient;
import org.gt.shipping.service.routing.TestRoutingService;
import org.gt.shipping.service.security.TestSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
public class TestConfiguration {
    @Bean
    public TestSecurityService securityService() { return new TestSecurityService();}

    @Bean
    public TestCargoService cargoService() { return new TestCargoService();}

    @Bean
    public Client client() { return ClientBuilder.newClient(); }

    @Bean
    public KafkaClient kafkaClient() { return new KafkaClient();}

    @Bean
    public TestRoutingService routingService() { return new TestRoutingService(); }
}
