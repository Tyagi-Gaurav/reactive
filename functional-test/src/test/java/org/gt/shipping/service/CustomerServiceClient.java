package org.gt.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Scope("cucumber-glue")
@Controller
public class CustomerServiceClient {

    @LocalServerPort
    private int port;

    public String healthcheckUrl() {
        return String.format("http://localhost:%d/actuator/health", port);
    }
}
