package org.gt.shipping.carrier.service;

import org.gt.shipping.carrier.domain.ImmutableRoute;
import org.gt.shipping.carrier.repository.RouteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarrierControllerService {

    @Autowired
    private RouteDAO routeDAO;

    public List<ImmutableRoute> getRoutes(String source, String dest) {
        return routeDAO.findAllRoutes(source, dest);
    }
}
