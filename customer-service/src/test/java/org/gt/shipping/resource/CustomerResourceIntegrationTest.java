package org.gt.shipping.resource;

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
@SpringBootTest(classes = {CustomerResource.class})
@AutoConfigureMockMvc
public class CustomerResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void exampleTest() throws Exception {
        //When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/shipping/customer/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }
}