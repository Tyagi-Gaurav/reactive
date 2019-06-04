package org.gt.shipping;

import org.gt.shipping.service.security.OAuthTokenResponse;
import org.gt.shipping.service.security.SecurityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestConfiguration.class, loader= AnnotationConfigContextLoader.class)
public class SmokeTest {

    @Autowired
    private SecurityService securityService;

    @Test
    @DisplayName("End To End Smoke Test")
    void smokeTest() {
        OAuthTokenResponse oauthToken = securityService.getOauthToken();

        assertThat(oauthToken.accessToken()).isNotNull();
    }
}
