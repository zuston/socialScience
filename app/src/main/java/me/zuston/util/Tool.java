package me.zuston.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by zuston on 17/1/18.
 */
public class Tool {
    public static ArrayList<String> jsonArray2List(JsonArray jsonElements){
        ArrayList<String> arrayList = new ArrayList<String>();
        for (JsonElement jsonObject:jsonElements){
            arrayList.add(jsonObject.getAsString());
        }
        return arrayList;
    }


}
