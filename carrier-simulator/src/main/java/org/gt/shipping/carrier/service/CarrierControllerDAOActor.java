package org.gt.shipping.carrier.service;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigBeanFactory;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationRequest;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationResponse;
import org.gt.shipping.carrier.repository.RouteDAO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarrierControllerDAOActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private RouteDAO routeDAO;

    @Inject
    public CarrierControllerDAOActor(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    public static Props props(RouteDAO routeDAO) {
        return Props.create(CarrierControllerDAOActor.class
                , () -> new CarrierControllerDAOActor(routeDAO));
    }

    @Override
    public void preStart() {
        log.info("CarrierControllerDAOActor started");
    }

    @Override
    public void postStop() {
        log.info("CarrierControllerDAOActor stopped");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ImmutableRouteInformationRequest.class, this::onRouteInformationRequest)
                .match(Terminated.class, this::onTerminated)
                .matchAny(o -> log.error("Unknown message received {}", o))
                .build();
    }

    private void onTerminated(Terminated terminated) {
        log.info("Terminating actor");
        getContext().stop(getSelf());
        log.info("Actor Terminated");
    }

    private void onRouteInformationRequest(ImmutableRouteInformationRequest immutableRoute) {
        List<ImmutableRoute> allRoutes = routeDAO.findAllRoutes(
                immutableRoute.sourceAirport(),
                immutableRoute.destinationAirport());

        ImmutableRouteInformationResponse response = ImmutableRouteInformationResponse.builder()
                .routes(allRoutes)
                .build();

        getSender().tell(response, getSelf());
    }
}
