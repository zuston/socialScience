package me.zuston.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.zuston.bean.CityBean;
import me.zuston.bean.CountyBean;
import me.zuston.dao.AreaDao;
import me.zuston.util.MongoDB;
import org.bson.Document;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zuston on 17/1/18.
 */
public class AreaService {
    public static List getInfo(HashMap<String,String> hashMap){
        String content = hashMap.get("searchContent");
        String choice  = hashMap.get("radio4");
        String option  = hashMap.get("radio1");

        AreaDao areaDao = new AreaDao();
        if(choice.equals("cityOption")){
            List<CityBean> cityBeanList = areaDao.getFromCity(content,option);
            return cityBeanList;
        }
        List<CountyBean> countyBeanList = areaDao.getFromCounty(content,option);

        return countyBeanList;

    }

    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("searchContent","522630");
        hashMap.put("radio4","countyOption");
        hashMap.put("radio1","code");
        List<CountyBean> countyBeanList = AreaService.getInfo(hashMap);
        for(CountyBean countyBean:countyBeanList){
            System.out.println(countyBean.area);
        }
    }
}
