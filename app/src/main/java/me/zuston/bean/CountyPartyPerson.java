package me.zuston.bean;

/**
 * Created by zuston on 17/1/19.
 */
public class CountyPartyPerson {
    /**
     * "origin" : "",
     "name" : "杨德涛",
     "age" : "",
     "after" : "",
     "year" : "2009",
     "birth" : "",
     "tenure" : "",
     "promote" : "",
     "before" : ""
     */

    public String origin;
    public String name;
    public String age;
    public String after;
    public String year;
    public String birth;
    public String tenure;
    public String promote;
    public String before;

    public CountyPartyPerson(String origin, String name, String age, String after, String year, String birth, String tenure, String promote, String before) {
        this.origin = origin;
        this.name = name;
        this.age = age;
        this.after = after;
        this.year = year;
        this.birth = birth;
        this.tenure = tenure;
        this.promote = promote;
        this.before = before;
    }
}
