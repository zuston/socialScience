package me.zuston.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by zuston on 17/1/18.
 */
public class MongoDB {

    public static String host = "localhost";
    public static int    port = 27017;
    public static String dbName = "socialScience";

    public static MongoDatabase mongoDatabase = null;

    public MongoDB() {

    }

    public static MongoDatabase getInstance(){
        if(MongoDB.mongoDatabase==null){
            MongoClient mongoClient = new MongoClient(MongoDB.host,MongoDB.port);
            MongoDB.mongoDatabase = mongoClient.getDatabase(MongoDB.dbName);
        }
        return MongoDB.mongoDatabase;
    }

}
