package org.gt.shipping.stepdefs;

import cucumber.api.java8.En;
import org.gt.shipping.BaseFunctionalTest;
import org.gt.shipping.service.CustomerServiceClient;
import org.gt.shipping.system.IdentityRequestCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Scope("cucumber-glue")
public class HealthCheckStepDefs extends BaseFunctionalTest implements En {

    @Autowired
    private CustomerServiceClient customerServiceClient;

    public HealthCheckStepDefs() {
        When("^healthcheck endpoint for customer service is invoked$", () -> {
            executeGet(customerServiceClient.healthcheckUrl(), new IdentityRequestCallback());
        });

        Then("^a status of (\\d+) is returned$", (Integer arg0) -> {
            assertThat(getLastResponse().isError()).isFalse();
            assertThat(getLastResponse().getStatusCode()).isEqualTo(HttpStatus.OK.value());
        });
        And("^the healthcheck response has status \"([^\"]*)\"$", (String arg0) -> {
            Map<String, String> body = getLastResponse().getBody(Map.class);
            assertThat(body.get("status")).isEqualTo("UP");
        });
    }
}
