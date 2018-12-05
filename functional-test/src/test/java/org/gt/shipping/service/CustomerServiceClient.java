package org.gt.shipping.service;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("cucumber-glue")
@Controller
public class CustomerServiceClient {

    @LocalServerPort
    private int port;

    public String healthcheckUrl() {
        return String.format("http://localhost:%d/actuator/health", port);
    }
}
