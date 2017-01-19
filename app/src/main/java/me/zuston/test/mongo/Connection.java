package me.zuston.test.mongo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import com.mongodb.client.model.Filters.*;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by zuston on 17/1/17.
 */
public class Connection {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("socialScience");
        MongoCollection<Document> collections = mongoDatabase.getCollection("city");

        System.out.println(collections.count());

        // 获取第一个文档
        Document firstDocument = collections.find().first();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(firstDocument.toJson());
        // 打成json数据
        System.out.println(firstDocument.toJson());
        // 一维
        System.out.println(jsonObject.get("province").getAsString());
        // 多维json解析
        System.out.println(
                jsonObject.get("mayor").getAsJsonObject()
                        .get("2003").getAsJsonObject()
                        .get("origin").getAsString()
        );



        // 获取全部文档
//        MongoCursor<Document> mongoCursor = collections.find().iterator();
//        try {
//            while (mongoCursor.hasNext()){
//                System.out.println(mongoCursor.next().toJson());
//            }
//        }finally {
//            mongoCursor.close();
//        }

        Document document = collections.find(Filters.eq("mayor.2003.origin","中央下放")).first();
        System.out.println(document.toJson());

        MongoCollection<Document> collection = mongoDatabase.getCollection("corrupt");
        JsonObject jsonObject1 = (JsonObject) parser.parse(collection.find().first().toJson());
        System.out.println(jsonObject1.get("_id").getAsJsonObject().get("$oid").getAsString());

    }


}
