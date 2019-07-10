package org.gt.shipping.cargo.resource;

import lombok.extern.slf4j.Slf4j;
import org.gt.shipping.KafkaClient;
import org.gt.shipping.MessageMetaData;
import org.gt.shipping.cargo.filter.UserContextHolder;
import org.gt.shipping.cargo.routing.RoutingServiceAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/shipping/cargo"})
@Slf4j
public class CargoResource {

    @Autowired
    private RoutingServiceAdaptor routingService;

    @Autowired
    private KafkaClient kafkaClient;

    @RequestMapping(method = {RequestMethod.POST}, value = {"/book"})
    public BookingResponseDTO book() {
        log.info("Request Received");
        kafkaClient.sendMessage("Hello World. Lets book cargo.", new MessageMetaData());

        String routingId = routingService.getRoutingId();

        log.info("Request ID {}", UserContextHolder.getContext().getRequestId());

        return new BookingResponseDTO("paymentTxId", "BookingId", routingId);
    }
}
