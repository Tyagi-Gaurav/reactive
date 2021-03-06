package org.gt.shipping.service.cargo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import static org.gt.shipping.service.cargo.ImmutableTestBookingResponse.*;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@Value.Style(builder = "new")
@JsonSerialize
@JsonDeserialize(builder = Builder.class)
public interface TestBookingResponse {
    String paymentTransactionId();

    String bookingId();

    String routingId();
}
