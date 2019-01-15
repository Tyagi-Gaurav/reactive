package org.gt.shipping.service;

import org.gt.shipping.resource.CargoManagerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("cucumber-glue")
@Controller
public class CargoManagerClient {
    @Autowired
    private CargoManagerResource cargoManagerResource;

    public CargoManagerResource getCargoManagerResource() {
        return cargoManagerResource;
    }
}
