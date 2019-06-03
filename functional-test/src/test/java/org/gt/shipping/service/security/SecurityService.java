package org.gt.shipping.service.security;

import org.gt.shipping.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements BaseService {

    @Override
    public String getHealthCheckUrl() {
        return "http://localhost:8091/actuator/health";
    }
}
