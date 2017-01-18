package me.zuston.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.zuston.bean.CityBean;
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
        String select  = hashMap.get("select");
        String choice  = hashMap.get("radio4");
        String option  = hashMap.get("radio1");

        AreaDao areaDao = new AreaDao();
        if(choice.equals("cityOption")){
            List<CityBean> cityBeanList = areaDao.getFromCity(content,option);
            return cityBeanList;
        }


        return null;

    }

    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("searchContent","451100");
        hashMap.put("radio4","cityOption");
        hashMap.put("radio1","code");
        AreaService.getInfo(hashMap);
    }
}
