package org.gt.shipping.carrier.repository;

import com.mongodb.client.MongoCollection;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.bson.Document;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan("org.gt.shipping.carrier.repository")
@TestPropertySource(properties = {"database.routeTable=route"})
public class RouteDAOTest {

    private static final String COLLECTION = "route";

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Ignore
    void shouldHaveAppropriateAnnotations() throws Exception {
        //When & Then
        assertThat(RouteDAO.class.isAnnotationPresent(Repository.class)).isTrue();
    }

    @BeforeEach
    void setUp() {
        mongoTemplate.createCollection(COLLECTION);
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION);
        collection.insertMany(Arrays.asList(
                getRouteDocument("JFK", "LHR", 189.39, "18.20"),
                getRouteDocument("JFK", "LHR", 2387.39, "1338.11")

        ));
    }

    private Document getRouteDocument(String sourceAiport, String destinationAirport, double distanceInKm, String price) {
        return new Document()
                .append("route",
                        new Document()
                                .append("src_apt", sourceAiport)
                                .append("dest_apt", destinationAirport)
                                .append("distance_in_km", distanceInKm)
                                .append("price", price)
                );
    }

    @Test
    void shouldRetrieveDataFromDatabase() throws Exception {
        //When
        List<ImmutableRoute> routes = routeDAO.findRoutes();

        //Then
        assertThat(routes.size()).isEqualTo(2);
        assertThat(routes.get(0).destinationAirport()).isEqualTo("LHR");
        assertThat(routes.get(0).sourceAirport()).isEqualTo("JFK");
    }
}