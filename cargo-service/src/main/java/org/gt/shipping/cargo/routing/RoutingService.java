package org.gt.shipping.cargo.routing;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class RoutingService {
    private Logger logger = LoggerFactory.getLogger(RoutingService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")

            },
            fallbackMethod = "defaultRoutingId",
            threadPoolKey = "routingService",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value="30"),
                    @HystrixProperty(name = "maxQueueSize", value="10")
            }
    )
    public String getRoutingId() {
        randomRunLong();
        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://routing-service/",
                HttpMethod.POST,
                null,
                String.class);

        return exchange.getBody();
    }

    private String defaultRoutingId() {
        return "myId";
    }

    private void randomRunLong() {
        Random random = new Random();
        int randomNum = random.nextInt(3) + 1;
        logger.info("Random Number {}", randomNum);

        if (randomNum == 3) sleep();
    }

    private void sleep() {
        try {
            logger.info("Sleeping ...");
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
