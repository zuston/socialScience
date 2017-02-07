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

    public String getOrigin() {
        return origin;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAfter() {
        return after;
    }

    public String getYear() {
        return year;
    }

    public String getBirth() {
        return birth;
    }

    public String getTenure() {
        return tenure;
    }

    public String getPromote() {
        return promote;
    }

    public String getBefore() {
        return before;
    }

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
