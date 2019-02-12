package org.gt.shipping.cargo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ServiceConfig {
    @Value("${kafka.host}")
    private String kafkaHost;
}
