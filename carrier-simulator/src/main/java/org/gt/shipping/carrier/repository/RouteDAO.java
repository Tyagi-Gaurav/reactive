package org.gt.shipping.carrier.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDAO {
    @Autowired
    private MongoTemplate mongoTemplate;


    public void getData() {

    }
}
