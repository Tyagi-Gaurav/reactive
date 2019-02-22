package org.gt.shipping.sg.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResponseFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();

        String correlationId = filterUtils.getCorrelationId();

        log.trace("Adding correlation Id to outbound headers {}", correlationId);

        currentContext.getResponse().addHeader(FilterUtils.CORRELATION_ID, correlationId);

        log.trace("Outgoing request complete for {}", currentContext.getRequest().getRequestURI());
        return null;
    }
}
