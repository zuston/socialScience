package me.zuston.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.zuston.bean.OfficerBean;
import me.zuston.bean.PersonBean;
import me.zuston.util.MongoDB;
import me.zuston.util.Tool;
import org.bson.Document;

import java.util.*;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Created by zuston on 17/1/18.
 */
public class OfficerService {
    public static Map<String, List> getInfo(HashMap<String,String> hashMap){
        String content = hashMap.get("searchContent");
        String select  = hashMap.get("select");
        String radio  = hashMap.get("radio1");

        MongoDatabase mongoDatabase = MongoDB.getInstance();
        MongoCollection<Document> mongoCollectionRenmin = mongoDatabase.getCollection("renmin");
        MongoCollection<Document> mongoCollectionPerson = mongoDatabase.getCollection("person");


        BasicDBObject condition = new BasicDBObject();
        if(radio.equals("name")){
            condition.put(radio,content);
        }else{
            Pattern pattern = Pattern.compile("^.*"+content+".*$", CASE_INSENSITIVE);
            condition.put(radio,pattern);
        }
        List<OfficerBean> officerBeen = new ArrayList<OfficerBean>();
        JsonParser parser = new JsonParser();
        for(Document document:mongoCollectionRenmin.find(condition)){
            JsonObject jsonObject = (JsonObject) parser.parse(document.toJson());
            officerBeen.add(new OfficerBean(
                    jsonObject.get("province").getAsString(),
                    jsonObject.get("duty").getAsString(),
                    jsonObject.get("birth").getAsString(),
                    jsonObject.get("resume").getAsString(),
                    jsonObject.get("nativePlace").getAsString(),
                    jsonObject.get("sex").getAsString(),
                    jsonObject.get("education").getAsString(),
                    jsonObject.get("name").getAsString(),
                    jsonObject.get("position").getAsString()
            ));
        }

        BasicDBObject conditionPerson = new BasicDBObject();
        Pattern pattern = Pattern.compile("^.*"+content+".*$", CASE_INSENSITIVE);
        conditionPerson.put("simpleInfo",pattern);
        List<PersonBean> personBeenList = new ArrayList<PersonBean>();
        for(Document document:mongoCollectionPerson.find(conditionPerson)){
            JsonObject jsonObject = (JsonObject) parser.parse(document.toJson());
            JsonArray paramDictJson = jsonObject.get("paramDict").getAsJsonArray();
            JsonArray resume = jsonObject.get("resume").getAsJsonArray();
            personBeenList.add(new PersonBean(
                    jsonObject.get("name").getAsString(),
                    jsonObject.get("simpleInfo").getAsString(),
                    Tool.jsonArray2List(paramDictJson),
                    Tool.jsonArray2List(resume)
            ));
        }

        Map<String,List> resHashMap = new HashMap<String, List>();
        resHashMap.put("officerBean",officerBeen);
        resHashMap.put("personBean",personBeenList);
        return resHashMap;
    }


    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("searchContent","马骥");
        hashMap.put("select","");
        hashMap.put("radio1","name");
        List<PersonBean> officerBeen = OfficerService.getInfo(hashMap).get("personBean");
        for(PersonBean officerBean:officerBeen){
            for(String str:officerBean.paramDict){
                System.out.println(str);
            }
        }
    }
}
