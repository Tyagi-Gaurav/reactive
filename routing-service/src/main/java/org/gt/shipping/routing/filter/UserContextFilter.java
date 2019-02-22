package org.gt.shipping.routing.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

//TODO Move into a library
@Component
@Slf4j
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        UserContextHolder.getContext()
                .setRequestId(getUUIDFromHeader(httpServletRequest));

        chain.doFilter(request, response);
    }

    private UUID getUUIDFromHeader(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(Headers.REQUEST_ID.getValue());
        log.trace("RequestId from request {}", header);

        if (header == null) {
            return UUID.randomUUID();
        } else {
            return UUID.fromString(header);
        }
    }
}
