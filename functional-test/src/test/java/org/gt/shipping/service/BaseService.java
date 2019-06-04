package org.gt.shipping.service;

public interface BaseService {
    default String getHealthCheckUrl() {
        return "/actuator/health";
    }
}
