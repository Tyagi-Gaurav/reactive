import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.gt.shipping.BaseFunctionalTest;
import org.gt.shipping.service.CargoManagerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")
public class CargoManagerStepDefs extends BaseFunctionalTest implements En {

    @Autowired
    private CargoManagerClient cargoManagerClient;

    public CargoManagerStepDefs() {
        Given("^the registered user intends to see a list of available routes$", () -> {
            cargoManagerClient
                    .getCargoManagerResource()
                    .createDefaultRequest();

            // Create Request
        });

        When("^the registered user attempts to retrieve a list of available routes$", () -> {
            // Invoke cargo resource for retrieving the list of eligible routes

        });
    }
}
