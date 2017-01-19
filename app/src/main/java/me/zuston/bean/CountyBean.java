package me.zuston.bean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zuston on 17/1/19.
 */
public class CountyBean {
/**
 * "province" : "贵州省",
 "city" : "黔东南苗族侗族自治州",
 "gov" : [
 {
 "relationId" : ObjectId("587f5321f910192ba3200cd1"),
 "name" : "潘亮",
 "year" : "2005"
 }
 ],
 "area" : "台江县",
 "code" : "522630",
 "party" : [
 {
 "origin" : "",
 "name" : "龙昌海",
 "age" : "",
 "after" : "",
 "year" : "2000",
 "birth" : "",
 "tenure" : "",
 "promote" : "",
 "before" : ""
 },
 */

    public String province;
    public String city;
    public String area;
    public String code;
    public HashMap<String,CountyGovPerson> countyGovPersonHashMap = new HashMap<String, CountyGovPerson>();
    public HashMap<String,CountyPartyPerson> countyPartyPersonHashMap = new HashMap<String, CountyPartyPerson>();

    public CountyBean(String province, String city, String area, String code, HashMap<String, CountyGovPerson> countyGovPersonHashMap, HashMap<String, CountyPartyPerson> countyPartyPersonHashMap) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.code = code;
        this.countyGovPersonHashMap = countyGovPersonHashMap;
        this.countyPartyPersonHashMap = countyPartyPersonHashMap;
    }
}
