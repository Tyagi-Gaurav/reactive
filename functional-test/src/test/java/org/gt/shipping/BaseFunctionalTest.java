package org.gt.shipping;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseFunctionalTest {

    @LocalServerPort
    protected int port;

    private final RestTemplate restTemplate;

    public BaseFunctionalTest() {
        restTemplate = new RestTemplate();
    }
}
