package org.gt.shipping.service.cargo;

import org.gt.shipping.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

public class CargoService implements BaseService {
    @Autowired
    Client client;

    private String hostname = "localhost";
    private String protocol = "http";
    private int port = 8081;
    private String bookingUrl = "v1/shipping/cargo/book";

    public BookingResponse book(String oauthToken) {
        String url = String.format("%s://%s:%d/%s", protocol, hostname, port, bookingUrl);

        Response response = client.target(URI.create(url).normalize())
                .request()
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", oauthToken))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post(Entity.text(""));

        return response.readEntity(BookingResponse.class);
    }

}
