package org.gt.shipping.carrier.resource.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gt.shipping.carrier.domain.Route;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonDeserialize(builder = org.gt.shipping.carrier.resource.response.ImmutableRouteResponse.Builder.class)
public interface RouteResponse {
    List<Route> routes();
}
