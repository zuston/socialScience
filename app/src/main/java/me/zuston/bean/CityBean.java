package me.zuston.bean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zuston on 17/1/18.
 */
public class CityBean {
    /**
     * "province" : "广西",
     "city" : "贺州市",
     "code" : "451100",
     "mayor" : {
     "2003" : {
     "origin" : "外地晋升，省委经历",
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
     },
     */

    public String province;
    public String city;
    public String code;

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMayor(HashMap<String, CityPersonBean> mayor) {
        this.mayor = mayor;
    }

    public String getProvince() {

        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public HashMap<String, CityPersonBean> getMayor() {
        return mayor;
    }

    public CityBean(String province, String city, String code, HashMap<String, CityPersonBean> mayor) {
        this.province = province;
        this.city = city;
        this.code = code;
        this.mayor = mayor;
    }

    public HashMap<String,CityPersonBean> mayor = new HashMap<String, CityPersonBean>();
//    public List<CityPersonBean> mayor = new HashMap<>();
}
