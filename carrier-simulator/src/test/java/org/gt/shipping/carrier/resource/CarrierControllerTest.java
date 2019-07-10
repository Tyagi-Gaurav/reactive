package org.gt.shipping.carrier.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarrierController.class)
public class CarrierControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnLegResponseGivenStartAndEndLocation() throws Exception {
        //Given

        //When
        //mockMvc.perform(get)

        //Then
    }
}
