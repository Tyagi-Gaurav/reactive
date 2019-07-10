package org.gt.shipping.carrier.repository;

import com.mongodb.MongoClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class RouteDAOTest {
    @InjectMocks
    private RouteDAO routeDAO;

    @MockBean
    private MongoClient mongoClient;

    @Test
    public void shouldHaveAppropriateAnnotations() throws Exception {
        //When & Then
        assertThat(RouteDAO.class.isAnnotationPresent(Repository.class)).isTrue();
    }

    @Test
    public void shouldRetrieveDataFromDatabase() throws Exception {
        //Given

        //When
        routeDAO.getData();

        //Then
    }
}