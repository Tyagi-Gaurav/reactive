package org.gt.shipping.carrier.resource;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.TestActor;
import akka.testkit.javadsl.TestKit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.ImmutableRouteEdge;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationResponse;
import org.gt.shipping.carrier.domain.ImmutableRouteNode;
import org.gt.shipping.carrier.domain.RouteInformationResponse;
import org.gt.shipping.carrier.resource.response.ImmutableRouteResponse;
import org.gt.shipping.carrier.resource.response.RouteResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarrierControllerTest.TestConfiguration.class)
public class CarrierControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Named("carrierDaoActor")
    private ActorRef carrierControllerDAOActor;

    private static RouteInformationResponse ROUTE = ImmutableRouteInformationResponse.builder()
            .routes(ImmutableRouteNode.of("source",
                    Collections.singletonList(ImmutableRouteEdge
                            .of(ImmutableRoute.builder()
                                            .airlineCode("ac")
                                            .destinationAirport("destination")
                                            .sourceAirport("source")
                                            .id("id")
                                            .distanceInKm(2.11)
                                            .price(BigDecimal.TEN)
                                            .airlineId("aid")
                                            .build(),
                                    ImmutableRouteNode.of("destination", Collections.emptyList())))))
            .build();

    @Test
    public void shouldReturnLegResponseGivenStartAndEndLocation() throws Exception {
        //Given
        String sourceAirport = "JFK";
        String destinationAirport = "LHR";

        //When
        MvcResult mvcResult = mockMvc.perform(get("/route")
                .param("source", sourceAirport)
                .param("dest", destinationAirport)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted()).andReturn();

        MvcResult finalResult = mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andReturn();

        //Then
        assertThat(finalResult.getResponse()).isNotNull();
        String contentAsString = finalResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        RouteResponse routeResponse = objectMapper.readValue(contentAsString,
                RouteResponse.class);

        assertThat(routeResponse).isNotNull();
        assertThat(routeResponse).isEqualTo(expectedResponse(ROUTE));
    }

    private Object expectedResponse(RouteInformationResponse routeInformationResponse) {
        return ImmutableRouteResponse.builder()
                .routes(routeInformationResponse.routes())
                .build();
    }

    @Configuration
    @EnableConfigurationProperties
    public static class TestConfiguration {
        @Bean
        public CarrierController carrierController(@Named("carrierDaoActor") ActorRef carrierDaoActor) {
            return new CarrierController(carrierDaoActor);
        }

        @Bean(name = "carrierDaoActor")
        public ActorRef actorRef() {
            ActorSystem actorSystem = ActorSystem.create();
            TestKit testKit = new TestKit(actorSystem);

            testKit.setAutoPilot(new TestActor.AutoPilot() {
                @Override
                public TestActor.AutoPilot run(ActorRef sender, Object msg) {
                    sender.tell(ROUTE, ActorRef.noSender());
                    return noAutoPilot();
                }
            });

            return testKit.getRef();
        }
    }
}
