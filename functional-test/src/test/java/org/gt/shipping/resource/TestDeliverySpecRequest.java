package org.gt.shipping.resource;

import org.immutables.value.Value;

@Value.Immutable
public abstract class TestDeliverySpecRequest {
    public abstract String origin();

    public abstract String destination();

    public abstract String startDate();
}
