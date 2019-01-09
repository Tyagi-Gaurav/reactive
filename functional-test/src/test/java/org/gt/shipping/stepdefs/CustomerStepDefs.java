package org.gt.shipping.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import org.gt.shipping.BaseFunctionalTest;
import org.gt.shipping.service.CustomerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")fe
public class CustomerStepDefs extends BaseFunctionalTest implements En {
    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Given("^customer intends to register with the application without a price plan$")
    public void customerIntendsToRegisterWithTheApplicationWithoutAPricePlan() throws Throwable {
    }

    @When("^customer attempts to register with the application$")
    public void customerAttemptsToRegisterWithTheApplication() throws Throwable {
        executePost(customerServiceClient.registerUserUrl(), customerServiceClient.registerRequest());
    }

    @Then("^they are registered with a default price plan$")
    public void theyAreRegisteredWithADefaultPricePlan() throws Throwable {

    }
}
