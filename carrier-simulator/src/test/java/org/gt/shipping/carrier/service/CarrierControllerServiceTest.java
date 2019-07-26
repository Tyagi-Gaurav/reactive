package org.gt.shipping.carrier.service;

import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.Route;
import org.gt.shipping.carrier.repository.RouteDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarrierControllerServiceTest {

    @InjectMocks
    private CarrierControllerService carrierControllerService;

    @Mock
    private RouteDAO routeDAO;

    @Test
    void shouldReturn_Routes() {
        //given
        String sourceAirport = "source";
        String destAirport = "dest";
        List<ImmutableRoute> expectedRoutes = Collections.singletonList(getDefaultRoute(sourceAirport, destAirport));
        given(routeDAO.findAllRoutes(sourceAirport, destAirport)).willReturn(expectedRoutes);

        //when
        List<ImmutableRoute> routes = carrierControllerService.getRoutes(sourceAirport, destAirport);

        //then
        assertThat(routes).isEqualTo(expectedRoutes);
        verify(routeDAO).findAllRoutes(sourceAirport, destAirport);
    }

    private ImmutableRoute getDefaultRoute(String sourceAirport, String destAirport) {
        return ImmutableRoute.builder()
                .id("test")
                .sourceAirport(sourceAirport)
                .destinationAirport(destAirport)
                .price(BigDecimal.TEN)
                .distanceInKm(1293.22)
                .build();
    }
}