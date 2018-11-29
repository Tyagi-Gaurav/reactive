package org.gt.shipping.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("cucumber-glue")
@Service
public class CustomerService {
    public void healthcheck() {
        System.out.println("Healthcheck invoked");
    }
}
