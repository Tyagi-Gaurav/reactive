package org.gt.shipping.service;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("cucumber-glue")
@Controller
public class CustomerServiceClient {

    private static final String CONTENT_TYPE = "application/vnd.customer.register.v1+json";

    @LocalServerPort
    private int port;

    private String host = "localhost";

    public String healthcheckUrl() {
        return String.format("http://%s:%d/actuator/health", host, port);
    }

    public String registerUserUrl() {
        return String.format("http://%s:%d/shipping/customer/register", host, port);
    }

}
