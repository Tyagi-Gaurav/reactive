package org.gt.shipping.cargo.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {
    private String paymentTransactionId;
    private String bookingId;
    private String routingId;
}
