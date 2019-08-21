package org.gt.shipping.carrier.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationRequest;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationResponse;
import org.gt.shipping.carrier.domain.ImmutableRouteNode;
import org.gt.shipping.carrier.domain.RouteNode;
import org.gt.shipping.carrier.repository.RouteDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CarrierControllerDAOActorTest {
    static ActorSystem actorSystem;
    private TestKit probe;

    @Mock
    private RouteDAO routeDAO;

    @BeforeEach
    void setUp() {
        actorSystem = ActorSystem.create();
        probe = new TestKit(actorSystem);
    }

    @AfterEach
    void tearDown() {
        if (actorSystem != null) {
            actorSystem.terminate();
        }
    }

    @Test
    void shouldReturnRoutesForGivenParameters() {
        //given
        ActorRef actorRef = actorSystem.actorOf(CarrierControllerDAOActor.props(routeDAO));
        String sourceAirport = "source";
        String destinationAirport = "destination";
        RouteNode expectedRoutes = defaultRoute(sourceAirport, destinationAirport);
        ImmutableRouteInformationResponse expectedResponse = ImmutableRouteInformationResponse.builder()
                .routes(expectedRoutes)
                .build();
        given(routeDAO.findRouteGraph(sourceAirport, destinationAirport))
                .willReturn(expectedRoutes);

        //when
        ImmutableRouteInformationRequest input = ImmutableRouteInformationRequest.builder()
                .sourceAirport(sourceAirport)
                .destinationAirport(destinationAirport)
                .build();
        actorRef.tell(input, probe.getRef());

        //then
        assertThat(expectedResponse).isEqualTo(probe.expectMsgAnyClassOf(ImmutableRouteInformationResponse.class));
    }

    private RouteNode defaultRoute(String sourceAirport, String destinationAirport) {
        return ImmutableRouteNode.builder().build();
//        return ImmutableRoute.builder()
//                .destinationAirport(destinationAirport)
//                .sourceAirport(sourceAirport)
//                .id("test")
//                .addLegs(ImmutableRoute.builder()
//                                .sourceAirport(sourceAirport)
//                                .destinationAirport("DEL")
//                                .id("test.1")
//                                .price(BigDecimal.valueOf(2.3))
//                                .distanceInKm(200).build(),
//                        ImmutableRoute.builder()
//                                .sourceAirport("DEL")
//                                .destinationAirport(destinationAirport)
//                                .id("test.2")
//                                .price(BigDecimal.valueOf(3.5))
//                                .distanceInKm(300).build())
//                .distanceInKm(333)
//                .price(BigDecimal.TEN)
//                .build();
    }
}