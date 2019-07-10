package org.gt.shipping.resource;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerResourceTest {

    @Test
    public void shouldHaveCorrectAnnotations() throws Exception {
        //Given
        java.lang.reflect.Method testMethod = CustomerResourceTest.class.getMethod("testMethod");
        Annotation[] expectedAnnotations = testMethod.getAnnotations();

        //When
        Annotation[] annotations = CustomerResource.class.getMethod("getMessage").getAnnotations();

        //Then
        assertThat(annotations).isEqualTo(expectedAnnotations);
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/test"})
    public String testMethod() { return null; }
}
