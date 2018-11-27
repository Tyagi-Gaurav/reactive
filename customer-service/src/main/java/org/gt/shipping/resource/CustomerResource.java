package org.gt.shipping.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/shipping/customer"})
public class CustomerResource {

    @RequestMapping(method = {RequestMethod.GET}, value = {"/test"})
    public String getMessage() {
        return "Hello World";
    }

}
