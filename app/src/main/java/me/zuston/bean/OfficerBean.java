package me.zuston.bean;

/**
 * Created by zuston on 17/1/18.
 */
public class OfficerBean {
    public String province;
    public String duty;
    public String birth;
    public String resume;
    public String nativePlace;
    public String sex;
    public String education;
    public String name;
    public String position;

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProvince() {

        return province;
    }

    public String getDuty() {
        return duty;
    }

    public String getBirth() {
        return birth;
    }

    public String getResume() {
        return resume;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public String getSex() {
        return sex;
    }

    public String getEducation() {
        return education;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public OfficerBean(String province, String duty, String birth, String resume, String nativePlace, String sex, String education, String name, String position) {

        this.province = province;
        this.duty = duty;
        this.birth = birth;
        this.resume = resume;
        this.nativePlace = nativePlace;
        this.sex = sex;
        this.education = education;
        this.name = name;
        this.position = position;
    }
}
