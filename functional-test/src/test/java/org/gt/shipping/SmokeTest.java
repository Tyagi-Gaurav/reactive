package org.gt.shipping;

import org.gt.shipping.service.security.SecurityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestConfiguration.class, loader= AnnotationConfigContextLoader.class)
public class SmokeTest {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private Client client;

    @Test
    @DisplayName("Check Security Service - Health")
    void securityServiceHealthCheck() {
        Response healthcheck = healthcheck(securityService.getHealthCheckUrl());
        assertThat(healthcheck.getStatus()).isEqualTo(200);
    }

    private <T> Response healthcheck(String healthCheckUrl) {
        return client.target(URI.create(healthCheckUrl))
                .request().buildGet().invoke();
    }
}
