package org.gt.shipping.carrier.repository;

import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDAO {
    @Autowired
    private MongoDatabase mongoDatabase;


    public void getData() {

    }
}
