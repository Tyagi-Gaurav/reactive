package org.gt.shipping.carrier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class DBConfig extends AbstractMongoConfiguration  {

    @Value("${database.name}")
    private String dbName;

    @Value("${database.uri}")
    private String uri;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(uri));
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }
}
