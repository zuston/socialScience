package me.zuston.bean;

import java.util.ArrayList;

/**
 * Created by zuston on 17/1/18.
 */
public class PersonBean {

    public String name;
    public String simpleInfo;

    public void setName(String name) {
        this.name = name;
    }

    public void setSimpleInfo(String simpleInfo) {
        this.simpleInfo = simpleInfo;
    }

    public void setParamDict(ArrayList<String> paramDict) {
        this.paramDict = paramDict;
    }

    public void setResume(ArrayList<String> resume) {
        this.resume = resume;
    }

    public String getName() {
        return name;
    }

    public String getSimpleInfo() {
        return simpleInfo;
    }

    public ArrayList<String> getParamDict() {
        return paramDict;
    }

    public ArrayList<String> getResume() {
        return resume;
    }

    public PersonBean(String name, String simpleInfo, ArrayList<String> paramDict, ArrayList<String> resume) {

        this.name = name;
        this.simpleInfo = simpleInfo;
        this.paramDict = paramDict;
        this.resume = resume;
    }

    public ArrayList<String> paramDict;
    public ArrayList<String> resume;
}
