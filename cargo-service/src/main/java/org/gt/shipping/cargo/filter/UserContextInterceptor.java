package org.gt.shipping.cargo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
//TODO Test
@Slf4j
public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        if (headers != null) {
            Optional<UUID> requestId = Optional.ofNullable(UserContextHolder.getContext().getRequestId());
            Optional<String> authToken = Optional.ofNullable(UserContextHolder.getContext().getAuthToken());

            log.info("Passing requestId to routing service {}", requestId);
            log.info("Passing authId to routing service {}", authToken);

            requestId.ifPresent(rId -> headers.add(Headers.REQUEST_ID.toString(), rId.toString()));
            authToken.ifPresent(token -> headers.add(HttpHeaders.AUTHORIZATION, token));
        }

        return execution.execute(request, body);
    }
}
