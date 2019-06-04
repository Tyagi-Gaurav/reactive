package org.gt.shipping;

import org.gt.shipping.service.cargo.BookingResponse;
import org.gt.shipping.service.cargo.CargoService;
import org.gt.shipping.service.security.OAuthTokenResponse;
import org.gt.shipping.service.security.SecurityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestConfiguration.class, loader= AnnotationConfigContextLoader.class)
public class SmokeTest {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CargoService cargoService;

    @Test
    @DisplayName("End To End Smoke Test")
    void smokeTest() {
        //Call Security Service
        OAuthTokenResponse oauthToken = securityService.getOauthToken();
        assertThat(oauthToken.accessToken()).isNotNull();

        //Book Cargo
        BookingResponse bookingResponse = cargoService.book(oauthToken.accessToken());
        assertThat(bookingResponse.bookingId()).isNotNull();

        //Check Routing Id
        assertThat(bookingResponse.routingId()).isNotNull();
        assertThat(UUID.fromString(bookingResponse.routingId())).isInstanceOf(UUID.class);

        //Check Kafka Message

    }
}
