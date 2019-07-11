package org.gt.shipping.service.routing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@Value.Style(builder = "new")
@JsonSerialize
@JsonDeserialize(builder = org.gt.shipping.service.routing.ImmutableTestRoutingResponse.Builder.class)
public interface TestRoutingResponse {
    List<TestLegResponse> legResponse();
}
