package org.gt.shipping.routing.filter;

public enum Headers {
    REQUEST_ID("X-Request-Id");

    private String value;

    Headers(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
