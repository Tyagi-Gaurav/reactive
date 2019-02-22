package org.gt.shipping.routing.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping(value = {"/v1/shipping/routing"})
@Slf4j
public class RoutingResource {

    @Autowired
    RestTemplate template;

    @RequestMapping(method = {RequestMethod.POST}, value = {"/"})
    public String route() {
        log.info("Received Request");
        return UUID.randomUUID().toString();
    }
}
