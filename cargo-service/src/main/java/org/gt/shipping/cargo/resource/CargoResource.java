package org.gt.shipping.cargo.resource;

import org.gt.shipping.KafkaClient;
import org.gt.shipping.MessageMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = {"/v1/shipping/cargo"})
public class CargoResource {

    private Logger logger = LoggerFactory.getLogger(CargoResource.class);

    @Autowired
    private KafkaClient kafkaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = {RequestMethod.POST}, value = {"/book"})
    public BookingResponseDTO book() {
        logger.info("Request Received");
        kafkaClient.sendMessage("Hello World. Lets book cargo.", new MessageMetaData());
        List<ServiceInstance> instancesById = discoveryClient.getInstances("routing-service");

        String routingId = null;

        if (!instancesById.isEmpty()) {
            String uri = String.format("%s/v1/shipping/routing/",
                    instancesById.get(0).getUri());
            logger.info("Calling Routing service with uri {}", uri);
            ResponseEntity<String> exchange = restTemplate.exchange(
                    uri, HttpMethod.POST, null,
                    String.class);

            routingId = exchange.getBody();
        } else {
            //Log that routing Id not found
            System.out.println("Routing service not found.");
            logger.info("Routing service not found.");
        }

        return new BookingResponseDTO("paymentTxId", "BookingId", routingId);
    }
}
