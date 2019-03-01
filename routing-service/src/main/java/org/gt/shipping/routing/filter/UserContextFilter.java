package org.gt.shipping.routing.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

//TODO Test & Move into a library
@Component
@Slf4j
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        UserContextHolder.getContext()
                .setRequestId(getUUIDFromHeader(httpServletRequest));

        UserContextHolder.getContext()
                .setAuthToken(getAuthHeader(httpServletRequest));

        chain.doFilter(request, response);
    }

    private String getAuthHeader(HttpServletRequest httpServletRequest) {
        Optional<String> header = Optional.of(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        log.info("Auth header from request {}", header);

        return header.orElse(null);
    }

    private UUID getUUIDFromHeader(HttpServletRequest httpServletRequest) {
        Optional<String> header = Optional.ofNullable(httpServletRequest.getHeader(Headers.REQUEST_ID.getValue()));
        log.info("RequestId from request {}", header);

        return header.map(UUID::fromString).orElse(UUID.randomUUID());
    }
}
