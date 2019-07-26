package org.gt.shipping.carrier.resource;

import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.resource.response.ImmutableRouteResponse;
import org.gt.shipping.carrier.resource.response.RouteResponse;
import org.gt.shipping.carrier.service.CarrierControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CarrierController {

    @Autowired
    private CarrierControllerService carrierControllerService;

    @GetMapping(value = "/route",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RouteResponse> routes(
            @RequestParam("source") String source,
            @RequestParam("dest") String destination) {
        List<ImmutableRoute> routes = carrierControllerService.getRoutes(source, destination);
        ImmutableRouteResponse routeResponse = ImmutableRouteResponse.builder().routes(routes).build();
        return ResponseEntity.ok(routeResponse);
    }
}
