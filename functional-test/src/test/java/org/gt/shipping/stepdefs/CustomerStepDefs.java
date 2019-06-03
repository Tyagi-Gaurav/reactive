package org.gt.shipping.stepdefs;

import org.gt.shipping.service.CustomerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerStepDefs  {
    @Autowired
    private CustomerServiceClient customerServiceClient;

//    @Given("^customer intends to register with the application without a price plan$")
//    public void customerIntendsToRegisterWithTheApplicationWithoutAPricePlan() throws Throwable {
//    }
//
//    @When("^customer attempts to register with the application$")
//    public void customerAttemptsToRegisterWithTheApplication() throws Throwable {
//        executePost(customerServiceClient.registerUserUrl(), customerServiceClient.registerRequest());
//    }
//
//    @Then("^they are registered with a default price plan$")
//    public void theyAreRegisteredWithADefaultPricePlan() throws Throwable {
//
//    }
}
