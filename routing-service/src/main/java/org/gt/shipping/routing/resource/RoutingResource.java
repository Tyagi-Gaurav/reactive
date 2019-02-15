package org.gt.shipping.routing.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = {"/v1/shipping/routing"})
public class RoutingResource {

    @RequestMapping(method = {RequestMethod.POST}, value = {"/"})
    public String route() {
        return UUID.randomUUID().toString();
    }
}
