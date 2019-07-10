package org.gt.shipping.functional.routing;

import org.gt.shipping.TestConfiguration;
import org.gt.shipping.service.routing.TestRoutingService;
import org.gt.shipping.service.routing.TestLegResponse;
import org.gt.shipping.service.routing.TestRoutingResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= TestConfiguration.class, loader= AnnotationConfigContextLoader.class)
public class RoutingTest {

    @Autowired
    private TestRoutingService routingService;

    @Test
    @DisplayName("Given start and end locations, make routing service return options from a single carrier")
    void returnOptionsFromSingleCarrier() {
        //Call Routing Service
        TestRoutingResponse routes = routingService.getRoutes("start", "End");

        //Check Options Returned in Response
        assertThat(routes).isNotNull();
        List<TestLegResponse> legResponses = routes.legResponse();
        assertThat(legResponses).isNotEmpty();

        assertThat(legResponses.size()).isGreaterThan(0);
    }
}
