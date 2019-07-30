package org.gt.shipping.carrier.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonDeserialize(builder = org.gt.shipping.carrier.domain.ImmutableRouteInformationResponse.Builder.class)
public interface RouteInformationResponse {
    List<Route> routes();
}
