package org.gt.shipping.carrier.repository;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.*;

@Component
public class RouteDAO {
    @Value("${database.routeTable}")
    private String collection;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ImmutableRoute> findDirectRoutes(String source, String destination) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(this.collection);

        FindIterable<Document> documents = collection.find(Filters.and(
                Filters.eq("route.dest_apt", destination),
                Filters.eq("route.src_apt", source)
        ));

        return mapFrom(documents);
    }

    public List<ImmutableRoute> findAllRoutes(String source, String destination) {
        List<ImmutableRoute> routes = getRoutesFor("route.src_apt", source);
        routes.addAll(getRoutesFor("route.dest_apt", destination));

        HashMap<String, ImmutableRoute> uniqueRoutes = routes.stream()
                .collect(HashMap::new,
                        (stringImmutableRouteHashMap, immutableRoute) ->
                                stringImmutableRouteHashMap.putIfAbsent(immutableRoute.id(), immutableRoute),
                        HashMap::putAll);

        List<ImmutableRoute> allRoutes = new ArrayList<>();

        List<ImmutableRoute> directRoutes = uniqueRoutes.values()
                .stream()
                .filter(immutableRoute -> isDirectRoute(source, destination, immutableRoute))
                .collect(Collectors.toList());

        Map<String, List<ImmutableRoute>> indirectRoutes = uniqueRoutes.values()
                .stream()
                .filter(immutableRoute -> !isDirectRoute(source, destination, immutableRoute))
                .collect(groupingBy(commonDestination(source), mapping(Function.identity(), toList())));


        allRoutes.addAll(directRoutes);
        allRoutes.addAll(indirectRoutes.values()
                .stream()
                .map(iroutes -> ImmutableRoute.builder().legs(iroutes)
                        .id(UUID.randomUUID().toString())
                        .distanceInKm(iroutes.stream().mapToDouble(ImmutableRoute::distanceInKm).sum())
                        .sourceAirport(source)
                        .destinationAirport(destination)
                        .price(iroutes.stream().map(ImmutableRoute::price).reduce(BigDecimal.ZERO, BigDecimal::add))
                        .build())
                .collect(toList()));

        return allRoutes;
    }

    private Function<ImmutableRoute, String> commonDestination(String source) {
        return immutableRoute -> {
            if (immutableRoute.sourceAirport().equals(source)) {
                return immutableRoute.destinationAirport();
            } else {
                return immutableRoute.sourceAirport();
            }
        };
    }

    private boolean isDirectRoute(String source, String destination, ImmutableRoute immutableRoute) {
        return immutableRoute.sourceAirport().equals(source) &&
                immutableRoute.destinationAirport().equals(destination);
    }

    private Optional<ImmutableRoute> getLegs(List<ImmutableRoute> routesToDestination, String destinationAirport) {
        return routesToDestination.stream()
                .filter(route -> route.sourceAirport().equals(destinationAirport))
                .findFirst();
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
