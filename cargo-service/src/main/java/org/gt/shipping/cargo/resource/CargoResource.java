package org.gt.shipping.cargo.resource;

import org.gt.shipping.KafkaClient;
import org.gt.shipping.MessageMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/shipping/cargo"})
public class CargoResource {

    @Autowired
    private KafkaClient kafkaClient;

    @RequestMapping(method = {RequestMethod.POST}, value = {"/book"})
    public BookingResponseDTO book() {
        kafkaClient.sendMessage("Hello World. Lets book cargo.", new MessageMetaData());
        return new BookingResponseDTO("paymentTxId", "BookingId");
    }
}
