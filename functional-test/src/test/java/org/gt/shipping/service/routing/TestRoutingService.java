package org.gt.shipping.service.routing;

import org.gt.shipping.service.BaseService;
import org.gt.shipping.service.security.TestSecurityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

public class TestRoutingService implements BaseService  {
    @Autowired
    Client client;

    @Autowired
    private TestSecurityService securityService;

    private String hostname = "localhost";
    private String protocol = "http";
    private int port = 8081;
    private String routingUrl = "v1/shipping/routing";

    public TestRoutingResponse getRoutes(String startLocation, String endLocation) {
        String url = String.format("%s://%s:%d/%s", protocol, hostname, port, routingUrl);

        Response response = client.target(URI.create(url).normalize())
                .queryParam("startLoc", startLocation)
                .queryParam("endLoc", endLocation)
                .request()
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", securityService.getOauthToken()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .get();

        return response.readEntity(TestRoutingResponse.class);
    }
}
