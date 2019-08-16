package org.gt.shipping.carrier.resource;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationRequest;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationResponse;
import org.gt.shipping.carrier.resource.response.ImmutableRouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.Duration;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/")
public class CarrierController {

    @Autowired
    private ActorRef carrierControllerDAOActor;

    @Inject
    public CarrierController(ActorRef carrierControllerDAOActor) {
        this.carrierControllerDAOActor = carrierControllerDAOActor;
    }

    @GetMapping(value = "/route",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Async
    public CompletionStage<Object> routes(
            @RequestParam("source") String source,
            @RequestParam("dest") String destination) {
        CompletionStage<Object> response = Patterns.ask(carrierControllerDAOActor,
                ImmutableRouteInformationRequest
                        .builder()
                        .sourceAirport(source)
                        .destinationAirport(destination)
                        .build(),
                Duration.ofMillis(2000));

        return response.handle((o, throwable) -> {
            if (throwable == null) {
                ImmutableRouteInformationResponse routeResponseFromDao =
                        (ImmutableRouteInformationResponse) o;

                ImmutableRouteResponse routeResponse = ImmutableRouteResponse.builder()
                        .routes(routeResponseFromDao.routes()).build();
                return ResponseEntity.ok(routeResponse);
            } else {
                throw new RuntimeException(throwable);
            }
        });
    }
}
