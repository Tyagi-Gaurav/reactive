package org.gt.shipping.stepdefs;

import org.gt.shipping.service.CustomerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthCheckStepDefs   {

    @Autowired
    private CustomerServiceClient customerServiceClient;

//    public HealthCheckStepDefs() {
//        When("^healthcheck endpoint for customer service is invoked$", () -> {
//            executeGet(customerServiceClient.healthcheckUrl());
//        });
//
//        Then("^a status of (\\d+) is returned$", (Integer arg0) -> {
//            assertThat(getLastResponse().isError()).isFalse();
//            assertThat(getLastResponse().getStatusCode()).isEqualTo(HttpStatus.OK.value());
//        });
//        And("^the healthcheck response has status \"([^\"]*)\"$", (String arg0) -> {
//            Map<String, String> body = getLastResponse().getBody(Map.class);
//            assertThat(body.get("status")).isEqualTo("UP");
//        });
//    }
}
