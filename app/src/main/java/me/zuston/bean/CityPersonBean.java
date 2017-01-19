package me.zuston.bean;

/**
 * Created by zuston on 17/1/18.
 */
public class CityPersonBean {
    /**
     * "origin" : "外地晋升，省委经历",
     "nativePlace" : "湖南",
     "provinceGovNativePlace" : "广西",
     "afterExperience" : "广西民政厅厅长，省级平调",
     "birth" : "1954.0",
     "year" : "2003",
     "education" : "",
     "name" : "陈利丹",
     "provincePartyNativePlace" : "湖南株洲人",
     "provinceGovName" : "陆兵",
     "beforeExperience" : "广西梧州市委副书记、市委党校校长",
     "promotion" : "",
     "provincePartyName" : "曹伯纯",
     "age" : "49"
     */



    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setProvinceGovNativePlace(String provinceGovNativePlace) {
        this.provinceGovNativePlace = provinceGovNativePlace;
    }

    public void setAfterExperience(String afterExperience) {
        this.afterExperience = afterExperience;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvincePartyNativePlace(String provincePartyNativePlace) {
        this.provincePartyNativePlace = provincePartyNativePlace;
    }

    public void setProvinceGovName(String provinceGovName) {
        this.provinceGovName = provinceGovName;
    }

    public void setBeforeExperience(String beforeExperience) {
        this.beforeExperience = beforeExperience;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public void setProvincePartyName(String provincePartyName) {
        this.provincePartyName = provincePartyName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOrigin() {

        return origin;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public String getProvinceGovNativePlace() {
        return provinceGovNativePlace;
    }

    public String getAfterExperience() {
        return afterExperience;
    }

    public String getBirth() {
        return birth;
    }

    public int getYear() {
        return year;
    }

    public String getEducation() {
        return education;
    }

    public String getName() {
        return name;
    }

    public String getProvincePartyNativePlace() {
        return provincePartyNativePlace;
    }

    public String getProvinceGovName() {
        return provinceGovName;
    }

    public String getBeforeExperience() {
        return beforeExperience;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getProvincePartyName() {
        return provincePartyName;
    }

    public int getAge() {
        return age;
    }

    public CityPersonBean(String origin, String nativePlace, String provinceGovNativePlace, String afterExperience, String birth, int year, String education, String name, String provincePartyNativePlace, String provinceGovName, String beforeExperience, String promotion, String provincePartyName, int age) {

        this.origin = origin;
        this.nativePlace = nativePlace;
        this.provinceGovNativePlace = provinceGovNativePlace;
        this.afterExperience = afterExperience;
        this.birth = birth;
        this.year = year;
        this.education = education;
        this.name = name;
        this.provincePartyNativePlace = provincePartyNativePlace;
        this.provinceGovName = provinceGovName;
        this.beforeExperience = beforeExperience;
        this.promotion = promotion;
        this.provincePartyName = provincePartyName;
        this.age = age;
    }

    public String origin;
    public String nativePlace;
    public String provinceGovNativePlace;
    public String afterExperience;
    public String birth;
    public int year;
    public String education;
    public String name;
    public String provincePartyNativePlace;
    public String provinceGovName;
    public String beforeExperience;
    public String promotion;
    public String provincePartyName;
    public int age;

}
