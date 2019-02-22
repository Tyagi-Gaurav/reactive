package org.gt.shipping.routing.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add(Headers.REQUEST_ID.toString(), UserContextHolder.getContext().getRequestId().toString());

        return execution.execute(request, body);
    }
}
