package com.hym.spring.learn.mongo.multi;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Arrays;


@Data
public class AbstractMongoConfig {

    protected String host;
    protected int port;
    protected String username;
    protected String password;
    protected String database;

    public MongoDbFactory mongoDbFactory() {
        MongoCredential credential = MongoCredential.createCredential(
                username, "learn", password.toCharArray());

        ServerAddress serverAddress = new ServerAddress(host, port);
        return new SimpleMongoDbFactory(new MongoClient(serverAddress, Arrays.asList(credential)), database);
    }

}

