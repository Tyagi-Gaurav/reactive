package org.gt.shipping.carrier.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

@Component
public class RouteDAO {
    @Value("${database.routeTable}")
    private String collection;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ImmutableRoute> findRoutes() {
        MongoCollection<Document> collection = mongoTemplate.getCollection(this.collection);

        FindIterable<Document> documents = collection.find(Filters.and(
                Filters.eq("route.dest_apt", "LHR"),
                Filters.eq("route.src_apt", "JFK")
        ));

        List<ImmutableRoute> routes = new ArrayList<>();

        documents.forEach((Consumer<Document>) document -> {

            Document route = (Document) document.get("route");

            if (!isNull(route)) {
                routes.add(ImmutableRoute.builder()
                        .id(String.valueOf(document.getObjectId("_id")))
                        .sourceAirport(route.getString("src_apt"))
                        .destinationAirport(route.getString("dest_apt"))
                        .distanceInKm(route.getDouble("distance_in_km"))
                        .price(new BigDecimal(route.getString("price")))
                        .build());
            }
        });

        return routes;
    }
}
