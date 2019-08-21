package org.gt.shipping.carrier.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.ImmutableRouteEdge;
import org.gt.shipping.carrier.domain.ImmutableRouteNode;
import org.gt.shipping.carrier.domain.RouteNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.gt.shipping.carrier.domain.ImmutableRouteNode.copyOf;
import static org.gt.shipping.carrier.domain.ImmutableRouteNode.of;

@Component
public class RouteDAO {
    @Value("${database.routeTable}")
    private String collection;

    @Autowired
    private MongoTemplate mongoTemplate;

    public RouteNode findRouteGraph(String source, String destination) {
        List<ImmutableRoute> routes = getRoutesFor("route.src_apt", source);
        routes.addAll(getRoutesFor("route.dest_apt", destination));

        HashMap<String, ImmutableRoute> uniqueRoutes = routes.stream()
                .collect(HashMap::new,
                        (stringImmutableRouteHashMap, immutableRoute) ->
                                stringImmutableRouteHashMap.putIfAbsent(immutableRoute.id(), immutableRoute),
                        HashMap::putAll);

        Map<String, RouteNode> nodeMap = new HashMap<>();
        uniqueRoutes.values().forEach(immutableRoute -> {
                nodeMap.putIfAbsent(immutableRoute.sourceAirport(), of(immutableRoute.sourceAirport(), new ArrayList<>()));
                nodeMap.putIfAbsent(immutableRoute.destinationAirport(), of(immutableRoute.destinationAirport(), new ArrayList<>()));
        });

        uniqueRoutes.values().forEach(route -> addEdgeToGraph(route, nodeMap));

        return nodeMap.get(source);
    }

    private void addEdgeToGraph(ImmutableRoute route, Map<String, RouteNode> nodeMap) {
        RouteNode sourceAirport = nodeMap.get(route.sourceAirport());
        RouteNode destinationAirport = nodeMap.get(route.destinationAirport());

        nodeMap.replace(sourceAirport.airport(),
                ImmutableRouteNode.builder().from(sourceAirport)
                .addEdges(ImmutableRouteEdge.of(route, destinationAirport)).build());
    }

    private List<ImmutableRoute> getRoutesFor(String fieldName, String fieldValue) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(this.collection);

        FindIterable<Document> documents = collection.find(Filters.and(
                Filters.eq(fieldName, fieldValue)
        ));

        return mapFrom(documents);
    }

    private List<ImmutableRoute> mapFrom(FindIterable<Document> documents) {
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
                        .airlineCode(route.getString("airline_code"))
                        .airlineId(route.getString("airline_id"))
                        .codeShare(route.getString("code_share"))
                        .equipCode(route.getString("equip_code"))
                        .legs(new ArrayList<>())
                        .build());
            }
        });

        return routes;
    }
}
