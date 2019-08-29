package org.gt.shipping.carrier.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.domain.ImmutableRouteEdge;
import org.gt.shipping.carrier.domain.ImmutableRouteNode;
import org.gt.shipping.carrier.domain.Route;
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
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
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

        Map<String, RouteNode> airportMap = new HashMap<>();
        Map<String, List<Route>> prospectiveEdges = new HashMap<>();
        uniqueRoutes.values().forEach(immutableRoute -> {
                prospectiveEdges.putIfAbsent(immutableRoute.sourceAirport(), new ArrayList());
                prospectiveEdges.putIfAbsent(immutableRoute.destinationAirport(), new ArrayList<>());
        });

        uniqueRoutes.values().forEach(route -> addEdgeToGraph(route, prospectiveEdges));

        return createGraphFor(airportMap, prospectiveEdges, source);
    }

    private RouteNode createGraphFor(Map<String, RouteNode> airportMap,
                                     Map<String, List<Route>> prospectiveEdges,
                                     String next) {
        List<Route> routes = prospectiveEdges.get(next);
        List<ImmutableRouteEdge> edges = routes.stream().map(route -> {
            Optional<RouteNode> routeNode = Optional.ofNullable(airportMap.get(next));
            return ImmutableRouteEdge.of(route, routeNode.orElse(createGraphFor(airportMap, prospectiveEdges, route.destinationAirport())));
        }).collect(Collectors.toList());
        ImmutableRouteNode routeNode = of(next, edges);
        airportMap.putIfAbsent(next, routeNode);
        return routeNode;
    }

    private void addEdgeToGraph(ImmutableRoute route, Map<String, List<Route>> prospectiveEdges) {
        prospectiveEdges.get(route.sourceAirport()).add(route);
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
                        .build());
            }
        });

        return routes;
    }
}
