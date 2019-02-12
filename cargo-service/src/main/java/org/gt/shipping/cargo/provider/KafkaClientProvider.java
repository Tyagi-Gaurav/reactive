package org.gt.shipping.cargo.provider;

import org.gt.shipping.KafkaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class KafkaClientProvider implements Supplier<KafkaClient> {
    @Value("${kafka.host}")
    private String bootStrapHost;

    @Override
    @Bean
    public KafkaClient get() {
        return new KafkaClient(bootStrapHost);
    }
}
