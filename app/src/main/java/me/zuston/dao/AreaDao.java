package me.zuston.dao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;
import me.zuston.bean.CityBean;
import me.zuston.bean.CityPersonBean;
import me.zuston.util.MongoDB;
import org.bson.BSONObject;
import org.bson.Document;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by zuston on 17/1/18.
 */
public class AreaDao {

    public MongoCollection<Document> mongoCollectionCity = null;
    public MongoCollection<Document> mongoCollectionCounty = null;

    public AreaDao(){
        MongoDatabase mongoDatabase = MongoDB.getInstance();
        mongoCollectionCity = mongoDatabase.getCollection("city");
        mongoCollectionCounty = mongoDatabase.getCollection("county");
    }

    public List<CityBean> getFromCity(String searchContent,String key){
        BasicDBObject condition = new BasicDBObject();
        if(key.equals("code")){
            condition.put(key,searchContent);
        }else{
            Pattern pattern = Pattern.compile("^.*"+searchContent+".*$",Pattern.CASE_INSENSITIVE);
            condition.put(key,pattern);
        }

        List<CityBean> cityBeanList = new ArrayList<CityBean>();
        JsonParser parser = new JsonParser();

        for (Document document:mongoCollectionCity.find(condition)){
            JsonObject jsonObject = (JsonObject) parser.parse(document.toJson());
            HashMap<String,CityPersonBean> cityPersonBeanHashMap = __mayorToHashMap(jsonObject);

            cityBeanList.add(new CityBean(
                    jsonObject.get("province").getAsString(),
                    jsonObject.get("city").getAsString(),
                    jsonObject.get("code").getAsString(),
                    cityPersonBeanHashMap
            ));
        }
        return cityBeanList;
    }

    private HashMap<String,CityPersonBean> __mayorToHashMap(JsonObject jsonObject){
        HashMap<String,CityPersonBean> hashMap = new HashMap<String, CityPersonBean>();
        JsonObject jsonObjectMayor = jsonObject.get("mayor").getAsJsonObject();
        for (Map.Entry<String,JsonElement> entrySet:jsonObjectMayor.entrySet()){
            String key = entrySet.getKey();
            JsonObject jsonObjectPerson = (JsonObject) entrySet.getValue();
            CityPersonBean cityPersonBean = new CityPersonBean(
                    jsonObjectPerson.get("origin").getAsString(),
                    jsonObjectPerson.get("nativePlace").getAsString(),
                    jsonObjectPerson.get("provinceGovNativePlace").getAsString(),
                    jsonObjectPerson.get("afterExperience").getAsString(),
                    jsonObjectPerson.get("birth").getAsString(),
                    jsonObjectPerson.get("year").getAsInt(),
                    jsonObjectPerson.get("education").getAsString(),
                    jsonObjectPerson.get("name").getAsString(),
                    jsonObjectPerson.get("provincePartyNativePlace").getAsString(),
                    jsonObjectPerson.get("provinceGovName").getAsString(),
                    jsonObjectPerson.get("beforeExperience").getAsString(),
                    jsonObjectPerson.get("promotion").getAsString(),
                    jsonObjectPerson.get("provincePartyName").getAsString(),
                    jsonObjectPerson.get("age").getAsInt()
            );
            hashMap.put(key,cityPersonBean);
        }
        return hashMap;
    }


}
