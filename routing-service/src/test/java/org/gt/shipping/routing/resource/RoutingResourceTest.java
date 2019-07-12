package org.gt.shipping.routing.resource;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RoutingResource.class)
@AutoConfigureMockMvc
public class RoutingResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Ignore
    public void exampleTest() throws Exception {
        //Given

        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/shipping/routing?startLoc="))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }
}