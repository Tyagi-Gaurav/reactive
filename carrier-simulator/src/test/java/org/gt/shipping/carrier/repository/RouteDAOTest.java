package org.gt.shipping.carrier.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.ImmutableRouteEdge;
import org.gt.shipping.carrier.domain.ImmutableRouteNode;
import org.gt.shipping.carrier.domain.RouteEdge;
import org.gt.shipping.carrier.domain.RouteNode;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private List<Document> dbRecords;

    @BeforeEach
    void setUp() {
        mongoTemplate.createCollection(COLLECTION);
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION);
        dbRecords = Arrays.asList(
                getRouteDocument("JFK", "LHR", 189.39, "18.20", "VG", "eee", "1", "11"),
                getRouteDocument("JFK", "DEL", 2387.39, "1338.11", "VG", "eee", "1", "22"),
                getRouteDocument("DEL", "CHG", 213.39, "4234.11", "VG", "eee", "1", "33"),
                getRouteDocument("DEL", "LHR", 213.39, "4234.11", "VG", "eee", "1", "44")

        );
        collection.insertMany(dbRecords);
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
    void shouldHaveAppropriateAnnotations() {
        //When & Then
        assertThat(RouteDAO.class.isAnnotationPresent(Component.class)).isTrue();
    }

    @Test
    void shouldRetrieveRoutesFromDatabase() {
        //given
        String source = "JFK";
        String destination = "LHR";
        RouteNode expectedRouteNode = getExpectedRouteNode();

        //When
        RouteNode routeNode = routeDAO.findRouteGraph(source, destination);

        //Then
        assertThat(routeNode).isNotNull();
        assertThat(routeNode.edges().size()).isEqualTo(expectedRouteNode.edges().size());
        assertThat(routeNode.airport()).isEqualTo(expectedRouteNode.airport());
        assertThat(routeNode.edges())
                //.usingElementComparatorIgnoringFields("id")
                .containsOnlyElementsOf(expectedRouteNode.edges());
    }

    private RouteNode getExpectedRouteNode() {
        ImmutableRouteNode lhrAirport = ImmutableRouteNode.of("LHR", Collections.emptyList());
        ImmutableRouteNode delAirport = ImmutableRouteNode.of("DEL", Collections.singletonList(getEdgesFor(dbRecords.get(3), lhrAirport)));
        List<RouteEdge> edgesForJfk = Arrays.asList(getEdgesFor(dbRecords.get(0), lhrAirport), getEdgesFor(dbRecords.get(1), delAirport));

        return ImmutableRouteNode.of("JFK", edgesForJfk);
    }

    private RouteEdge getEdgesFor(Document document, RouteNode destination) {
        return ImmutableRouteEdge.builder()
                .destination(destination)
                .route(ImmutableRoute.builder()
                        .price(new BigDecimal(getFieldFrom(document, "price")))
                        .distanceInKm(Double.valueOf(getFieldFrom(document, "distance_in_km")))
                        .sourceAirport(getFieldFrom(document, "src_apt"))
                        .destinationAirport(getFieldFrom(document, "dest_apt"))
                        .id("")
                        .airlineCode(getFieldFrom(document, "airline_code"))
                        .airlineId(getFieldFrom(document, "airline_id"))
                        .codeShare(getFieldFrom(document, "code_share"))
                        .equipCode(getFieldFrom(document, "equip_code"))
                        .build()).build();
    }

    private String getFieldFrom(Document record, String fieldName) {
        return ((Document) record.get("route")).get(fieldName).toString();
    }
}