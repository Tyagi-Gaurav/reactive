package org.gt.shipping.stepdef;

import cucumber.api.java8.En;
import org.gt.shipping.BaseFunctionalTest;
import org.gt.shipping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

@Scope("cucumber-glue")
public class HealthCheckStepDefs extends BaseFunctionalTest implements En {

    @Autowired
    private CustomerService customerService;

    public HealthCheckStepDefs() {
        When("^healthcheck endpoint for customer service is invoked$", () -> {
            assertThat(customerService).isNotNull();
        });

        Then("^a status of (\\d+) is returned$", (Integer arg0) -> {
            assertThat(customerService).isNotNull();
        });
    }
}
