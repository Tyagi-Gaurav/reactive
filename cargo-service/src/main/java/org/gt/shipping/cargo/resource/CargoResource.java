package org.gt.shipping.cargo.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/shipping/cargo"})
public class CargoResource {

    @RequestMapping(method = {RequestMethod.POST}, value = {"/book"})
    public BookingResponseDTO book() {

        return new BookingResponseDTO("paymentTxId", "BookingId");
    }
}
