package me.zuston.bean;

/**
 * Created by zuston on 17/1/19.
 */
public class CountyGovPerson {
    public String year;
    public PersonBean personBean = null;

    public String getYear() {
        return year;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public CountyGovPerson(String year, PersonBean personBean) {
        this.year = year;
        this.personBean = personBean;

    }

    public CountyGovPerson(String year) {
        this.year = year;
    }
}
