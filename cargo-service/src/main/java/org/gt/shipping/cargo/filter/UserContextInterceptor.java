package org.gt.shipping.cargo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        if (headers != null) {
            UUID requestId = UserContextHolder.getContext().getRequestId();

            log.info("Passing requestId to routing service {}", requestId);

            if (requestId != null) {
                headers.add(Headers.REQUEST_ID.toString(),
                        requestId.toString());
            }
        }

        return execution.execute(request, body);
    }
}
