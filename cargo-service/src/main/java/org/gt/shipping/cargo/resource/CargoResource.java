package org.gt.shipping.cargo.resource;

import org.gt.shipping.KafkaClient;
import org.gt.shipping.MessageMetaData;
import org.gt.shipping.cargo.routing.RoutingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/shipping/cargo"})
public class CargoResource {

    private Logger logger = LoggerFactory.getLogger(CargoResource.class);

    @Autowired
    private RoutingService routingService;

    @Autowired
    private KafkaClient kafkaClient;

    @RequestMapping(method = {RequestMethod.POST}, value = {"/book"})
    public BookingResponseDTO book() {
        logger.info("Request Received");
        kafkaClient.sendMessage("Hello World. Lets book cargo.", new MessageMetaData());

        String routingId = routingService.getRoutingId();

        return new BookingResponseDTO("paymentTxId", "BookingId", routingId);
    }
}
