package me.zuston.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import me.zuston.bean.CorruptBean;
import me.zuston.util.MongoDB;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

import com.mongodb.BasicDBObject;


/**
 * Created by zuston on 17/1/18.
 */
public class CorruptService {
    public static List<CorruptBean> getInfo(HashMap<String,String> hashMap){
        String content = hashMap.get("searchContent");
        String select  = hashMap.get("select");
        String radio1  = hashMap.get("radio1");
        String radio2  = hashMap.get("radio2");
        String radio3  = hashMap.get("radio3");

        MongoDatabase mongoDatabase = MongoDB.getInstance();
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("corrupt");

        int level = -1;
        int type = -1;

        String patternKey = "name";
        if(radio1.equals("zggb")){
            level = 0;
        }
        if(radio1.equals("sggb")){
            level = 1;
        }

        if(radio2.equals("zjsc")){
            type = 0;
        }

        if(radio2.equals("djcf")){
            type = 1;
        }


        if(radio3.equals("area")){
        }

        BasicDBObject condition = new BasicDBObject();

        if(level!=-1){
            condition.put("level",level);
        }
        if(type!=-1){
            condition.put("type",type);
        }
        Pattern pattern = Pattern.compile("^.*"+content+".*$", CASE_INSENSITIVE);
        condition.put("title",pattern);
        List<CorruptBean> corruptBeenList = new ArrayList<CorruptBean>();
        JsonParser parser = new JsonParser();
        for(Document document:mongoCollection.find(condition)){
            JsonObject jsonObject = (JsonObject) parser.parse(document.toJson());
            corruptBeenList.add(
                    new CorruptBean(
                            jsonObject.get("resourceUrl").getAsString(),
                            jsonObject.get("title").getAsString(),
                            jsonObject.get("level").getAsInt(),
                            jsonObject.get("content").getAsString(),
                            jsonObject.get("time").getAsString(),
                            jsonObject.get("resource").getAsString(),
                            jsonObject.get("type").getAsInt()
                    )
            );
        }

        return corruptBeenList;
    }

    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("searchContent","甘肃");
        hashMap.put("select","");
        hashMap.put("radio1","zggb");
        hashMap.put("radio2","zjsc");
        hashMap.put("radio3","name");
        CorruptService.getInfo(hashMap);
    }
}
