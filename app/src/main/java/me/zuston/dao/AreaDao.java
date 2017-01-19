package me.zuston.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;
import me.zuston.bean.*;
import me.zuston.util.MongoDB;
import me.zuston.util.Tool;
import org.bson.BSONObject;
import org.bson.Document;
import org.bson.types.Code;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by zuston on 17/1/18.
 */
public class AreaDao {

    public MongoCollection<Document> mongoCollectionCity = null;
    public MongoCollection<Document> mongoCollectionCounty = null;
    public MongoCollection<Document> mongoCollectionPerson = null;

    public AreaDao(){
        MongoDatabase mongoDatabase = MongoDB.getInstance();
        mongoCollectionCity = mongoDatabase.getCollection("city");
        mongoCollectionCounty = mongoDatabase.getCollection("county");
        mongoCollectionPerson = mongoDatabase.getCollection("person");
    }

    public List<CityBean> getFromCity(String searchContent,String key){
        // TODO: 17/1/19
        BasicDBObject condition = buildCondition(searchContent,"code");

        List<CityBean> cityBeanList = new ArrayList<CityBean>();
        JsonParser parser = new JsonParser();
        for (Document document:mongoCollectionCity.find(condition)){
            System.out.println("after loop");
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

    private HashMap<String, CityPersonBean> __mayorToHashMap(JsonObject jsonObject) {
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

    private BasicDBObject buildCondition(String content,String option){
        BasicDBObject condition = new BasicDBObject();
        if(option.equals("code")){
            condition.put(option,content);
        }else{
            Pattern pattern = Pattern.compile("^.*"+content+".*$",Pattern.CASE_INSENSITIVE);
            condition.put(option,pattern);
        }
        return condition;
    }


    public List<CountyBean> getFromCounty(String content, String option) {
        // TODO: 17/1/19
        option = "code";
        BasicDBObject condition = buildCondition(content,option);
        List<CountyBean> countyBeenList = new ArrayList<CountyBean>();
        JsonParser parser = new JsonParser();
        for (Document document:mongoCollectionCounty.find(condition)){
            JsonObject jsonObject = (JsonObject) parser.parse(document.toJson());
            countyBeenList.add(addToCountyBean(jsonObject));
        }
        return countyBeenList;
    }

    private CountyBean addToCountyBean(JsonObject jsonObject) {
        return new CountyBean(
                jsonObject.get("province").getAsString(),
                jsonObject.get("city").getAsString(),
                jsonObject.get("area").getAsString(),
                jsonObject.get("code").getAsString(),
                addToCountyGovPerson(jsonObject.get("gov").getAsJsonArray()),
                addToCountyPartyPerson(jsonObject.get("party").getAsJsonArray())
        );
    }

    private HashMap<String,CountyGovPerson> addToCountyGovPerson(JsonArray gov) {
        HashMap<String,CountyGovPerson> hm = new HashMap<String, CountyGovPerson>();

        for (JsonElement onePerson:gov){
            JsonObject personObject = (JsonObject) onePerson;
            String year = personObject.get("year").getAsString();
            PersonBean personBean = null;
            if(personObject.has("relationId")){
                String relationID = personObject.get("relationId").getAsJsonObject().get("$oid").getAsString();
                personBean = findByIdFromPerson(relationID);
            }
            hm.put(year,new CountyGovPerson(year,personBean));
        }
        return hm;
    }

    private PersonBean findByIdFromPerson(String relationID) {
        Document document = mongoCollectionPerson.find(Filters.eq("_id",new ObjectId(relationID))).first();
        if(document==null){
            return null;
        }
        JsonParser paser = new JsonParser();
        JsonObject jsonObject = (JsonObject) paser.parse(document.toJson());

        JsonArray paramDictJson = jsonObject.get("paramDict").getAsJsonArray();
        JsonArray resume = jsonObject.get("resume").getAsJsonArray();
        return new PersonBean(
                jsonObject.get("name").getAsString(),
                jsonObject.get("simpleInfo").getAsString(),
                Tool.jsonArray2List(paramDictJson),
                Tool.jsonArray2List(resume)
        );
    }

    private HashMap<String,CountyPartyPerson> addToCountyPartyPerson(JsonArray party) {
        HashMap<String,CountyPartyPerson> hm = new HashMap<String, CountyPartyPerson>();
        for(JsonElement element:party){
            JsonObject person = (JsonObject) element;
            String year = person.get("year").getAsString();
            hm.put(year,new CountyPartyPerson(
                    person.get("origin").getAsString(),
                    person.get("name").getAsString(),
                    person.get("age").getAsString(),
                    person.get("after").getAsString(),
                    person.get("year").getAsString(),
                    person.get("birth").getAsString(),
                    person.get("tenure").getAsString(),
                    person.get("promote").getAsString(),
                    person.get("before").getAsString()
            ));
        }
        return hm;
    }
}
