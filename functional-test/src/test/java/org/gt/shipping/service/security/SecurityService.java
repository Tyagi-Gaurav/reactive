package org.gt.shipping.service.security;

import org.gt.shipping.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class SecurityService implements BaseService {

    @Autowired
    Client client;

    private String hostname = "localhost";
    private String protocol = "http";
    private int port = 8091;
    private String oauthUrl = "oauth/token";

    public OAuthTokenResponse getOauthToken() {
        String url = String.format("%s://%s:%d/%s", protocol, hostname, port, oauthUrl);

        MultivaluedHashMap<String, String> formData = new MultivaluedHashMap<>();
        formData.add("grant_type", "password");
        formData.add("scope", "webclient");
        formData.add("username", "john.carnell");
        formData.add("password", "password1");

        Response response = client.target(URI.create(url).normalize())
                .request()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.ACCEPT, "*/*")
                .header(HttpHeaders.AUTHORIZATION, String.format("Basic %s",
                        base64Encode("routingservice", "thisissecret")))
                .post(Entity.form(formData));

        return response.readEntity(OAuthTokenResponse.class);
    }

    private String base64Encode(String username, String password) {
        return new String(Base64.getEncoder()
                .encode(String.format("%s:%s", username, password).getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
    }
}
