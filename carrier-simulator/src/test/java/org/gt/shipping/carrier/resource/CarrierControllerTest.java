package org.gt.shipping.carrier.resource;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.TestActor;
import akka.testkit.javadsl.TestKit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.ImmutableRouteInformationResponse;
import org.gt.shipping.carrier.domain.Route;
import org.gt.shipping.carrier.repository.RouteDAO;
import org.gt.shipping.carrier.resource.response.RouteResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarrierControllerTest.TestConfiguration.class)
public class CarrierControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Named("carrierDaoActor")
    private ActorRef carrierControllerDAOActor;

    @MockBean
    private RouteDAO routeDAO;
    private static ImmutableRoute ROUTE = ImmutableRoute.builder()
            .id("sss")
            .sourceAirport("source")
            .destinationAirport("destination")
            .distanceInKm(2.11)
            .price(BigDecimal.TEN)
            .build();;

    @Test
    public void shouldReturnLegResponseGivenStartAndEndLocation() throws Exception {
        //Given
        String sourceAirport = "JFK";
        String destinationAirport = "LHR";
        List<ImmutableRoute> expectedRoutes = defaultRoute();

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
        RouteResponse routeResponse = objectMapper.readValue(contentAsString, RouteResponse.class);

        assertThat(routeResponse).isNotNull();
        List<Route> allRoutes = routeResponse.routes();

        assertThat(allRoutes).isEqualTo(expectedRoutes);
    }

    private List<ImmutableRoute> defaultRoute() {

        return Collections.singletonList(ROUTE);
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
                    sender.tell(ImmutableRouteInformationResponse.builder()
                            .addRoutes(ROUTE)
                            .build(), ActorRef.noSender());
                    return noAutoPilot();
                }
            });

            return testKit.getRef();
        }
    }
}
