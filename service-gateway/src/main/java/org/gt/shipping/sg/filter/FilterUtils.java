package org.gt.shipping.sg.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String CORRELATION_ID = "X-Request-Id";

    public String getCorrelationId() {
        RequestContext currentContext = RequestContext.getCurrentContext();

        String header = currentContext.getRequest().getHeader(CORRELATION_ID);
        if (header != null) {
            return header;
        } else {
            return currentContext.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId) {
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }
}
