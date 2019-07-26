package org.gt.shipping.carrier.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
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

    @BeforeEach
    void setUp() {
        mongoTemplate.createCollection(COLLECTION);
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION);
        collection.insertMany(Arrays.asList(
                getRouteDocument("JFK", "LHR", 189.39, "18.20", "VG", "eee", "1", "11"),
                getRouteDocument("JFK", "DEL", 2387.39, "1338.11", "VG", "eee", "1", "22"),
                getRouteDocument("DEL", "CHG", 213.39, "4234.11", "VG", "eee", "1", "33"),
                getRouteDocument("DEL", "LHR", 213.39, "4234.11", "VG", "eee", "1", "44")

        ));
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(COLLECTION);
    }

    private static Document getRouteDocument(String sourceAiport,
                                             String destinationAirport,
                                             double distanceInKm,
                                             String price,
                                             String airlineCode,
                                             String airlineId,
                                             String codeShare,
                                             String equipCode) {
        return new Document()
                .append("route",
                        new Document()
                                .append("src_apt", sourceAiport)
                                .append("dest_apt", destinationAirport)
                                .append("distance_in_km", distanceInKm)
                                .append("price", price)
                                .append("airline_code", airlineCode)
                                .append("airline_id", airlineId)
                                .append("code_share", codeShare)
                                .append("equip_code", equipCode)
                );
    }

    @Test
    void shouldHaveAppropriateAnnotations() throws Exception {
        //When & Then
        assertThat(RouteDAO.class.isAnnotationPresent(Component.class)).isTrue();
    }

    @Test
    void shouldRetrieveDirectRoutesFromDatabase() {
        //given
        String source = "JFK";
        String destination = "LHR";

        //When
        List<ImmutableRoute> routes = routeDAO.findDirectRoutes(source, destination);

        //Then
        assertThat(routes.size()).isEqualTo(1);
        assertThat(routes.get(0).destinationAirport()).isEqualTo(destination);
        assertThat(routes.get(0).sourceAirport()).isEqualTo(source);
    }

    @Test
    void shouldRetrieveIndirectRoutesFromDatabase() {
        //given
        String source = "JFK";
        String destination = "LHR";

        //When
        List<ImmutableRoute> routes = routeDAO.findAllRoutes(source, destination);

        //Then
        assertThat(routes.size()).isEqualTo(2);
        ImmutableRoute directRoute = routes.get(0);
        assertThat(directRoute.destinationAirport()).isEqualTo(destination);
        assertThat(directRoute.sourceAirport()).isEqualTo(source);
        assertThat(directRoute.legs()).isEmpty();

        ImmutableRoute indirectRoute = routes.get(1);
        assertThat(indirectRoute.destinationAirport()).isEqualTo(destination);
        assertThat(indirectRoute.sourceAirport()).isEqualTo(source);
        assertThat(indirectRoute.legs().size()).isEqualTo(2);

        Route leg1 = indirectRoute.legs().get(0);
        Route leg2 = indirectRoute.legs().get(1);
        assertThat(leg1.sourceAirport()).isEqualTo(source);
        assertThat(leg1.destinationAirport()).isEqualTo("DEL");
        assertThat(leg2.sourceAirport()).isEqualTo("DEL");
        assertThat(leg2.destinationAirport()).isEqualTo(destination);
    }
}