package org.gt.shipping.cargo.resource;

import org.gt.shipping.KafkaClient;
import org.gt.shipping.MessageMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = {"/v1/shipping/cargo"})
public class CargoResource {

    private Logger logger = LoggerFactory.getLogger(CargoResource.class);

    @Autowired
    private KafkaClient kafkaClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = {RequestMethod.POST}, value = {"/book"})
    public BookingResponseDTO book() {
        logger.info("Request Received");
        kafkaClient.sendMessage("Hello World. Lets book cargo.", new MessageMetaData());

        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://routing-service/v1/shipping/routing/",
                HttpMethod.POST,
                null,
                String.class);

        String routingId = exchange.getBody();

        return new BookingResponseDTO("paymentTxId", "BookingId", routingId);
    }
}
