package org.gt.shipping.resource;

import org.immutables.value.Value;

@Value.Immutable
public abstract class TestCustomerRegisterRequest {
    public abstract String firstName();

    public abstract String lastName();

    public abstract String email();

    public abstract String mobile();
}