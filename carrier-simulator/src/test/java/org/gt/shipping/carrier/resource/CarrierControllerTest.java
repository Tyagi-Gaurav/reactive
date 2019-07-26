package org.gt.shipping.carrier.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.Route;
import org.gt.shipping.carrier.resource.response.RouteResponse;
import org.gt.shipping.carrier.service.CarrierControllerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarrierController.class)
public class CarrierControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarrierControllerService carrierControllerService;

    @Test
    public void shouldReturnLegResponseGivenStartAndEndLocation() throws Exception {
        //Given
        String sourceAiport = "JFK";
        String destinationAiport = "LHR";
        List<ImmutableRoute> expectedRoutes = defaultRoute();
        given(carrierControllerService.getRoutes(sourceAiport, destinationAiport)).willReturn(expectedRoutes);

        //When
        MvcResult mvcResult = mockMvc.perform(get("/route")
                .param("source", sourceAiport)
                .param("dest", destinationAiport))
                .andExpect(status().isOk()).andReturn();

        //Then
        assertThat(mvcResult.getResponse()).isNotNull();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        RouteResponse routeResponse = objectMapper.readValue(contentAsString, RouteResponse.class);

        assertThat(routeResponse).isNotNull();
        List<Route> allRoutes = routeResponse.routes();

        assertThat(allRoutes).isEqualTo(expectedRoutes);
    }

    private List<ImmutableRoute> defaultRoute() {
        return Collections.emptyList();
    }
}
