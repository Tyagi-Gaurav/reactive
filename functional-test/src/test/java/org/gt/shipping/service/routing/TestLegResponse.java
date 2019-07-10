package org.gt.shipping.service.routing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@Value.Style(builder = "new")
@JsonSerialize
@JsonDeserialize(builder = ImmutableTestLegResponse.Builder.class)
public interface TestLegResponse {
    LocalDateTime startTime();

    LocalDateTime endTime();

    String startLocation(); //TODO should be a domain object

    String endLocation(); //TODO should be a domain object

    BigDecimal price();

    String carrier(); //TODO should be a domain object

    String voyage(); //TODO should be a domain object
}
