package org.gt.shipping.carrier.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.math.BigDecimal;


@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonDeserialize(builder = org.gt.shipping.carrier.domain.ImmutableRoute.Builder.class)
public interface Route {
    @Value.Auxiliary
    String id();
    String sourceAirport();
    String destinationAirport();
    double distanceInKm();
    BigDecimal price();

    @Value.Default
    default String airlineCode() {
        return "";
    }

    @Value.Default
    default String airlineId() {
        return "";
    }

    @Value.Default
    default String codeShare() {
        return "";
    }

    @Value.Default
    default String equipCode() {
        return "";
    }
}