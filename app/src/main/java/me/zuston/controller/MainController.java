package me.zuston.controller;

import me.zuston.bean.CityBean;
import me.zuston.bean.CorruptBean;
import me.zuston.bean.CountyBean;
import me.zuston.service.AreaService;
import me.zuston.service.CorruptService;
import me.zuston.service.OfficerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zuston on 17/1/17.
 */
@Controller
public class MainController {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String main(ModelMap modelMap){
        return "index";
    }


    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        return "index";
    }

    @RequestMapping(path = "/search",method = RequestMethod.POST)
    public String search(@RequestParam("searchContent") String content,
                         @RequestParam("select") String select,
                         @RequestParam("optionRadio1")String optionRadio1,
                         @RequestParam("optionRadio2")String optionRadio2,
                         @RequestParam("optionRadio3")String optionRadio3,
                         @RequestParam("optionRadio4")String optionRadio4,
                         ModelMap modelMap){

        HashMap<String,String> container = new HashMap<String, String>();

        // TODO: 17/1/19
        content = "522630";
        optionRadio1 = "code";
        optionRadio4 = "countyOption";
        select = "area";

        container.put("searchContent",content);
        container.put("select",select);
        container.put("radio1",optionRadio1);
        container.put("radio2",optionRadio2);
        container.put("radio3",optionRadio3);
        container.put("radio4",optionRadio4);

        if(select.equals("corrupt")){
            List<CorruptBean> corruptBeenList = CorruptService.getInfo(container);
            modelMap.addAttribute("corruptBeenList",corruptBeenList);
            return "corrupt";
        }

        if(select.equals("officer")){
            HashMap<String,List> officerHashMap = (HashMap<String, List>) OfficerService.getInfo(container);
            modelMap.addAttribute("officerMap",officerHashMap);
            return "officer";
        }

        if(select.equals("area")){
            HashMap<String,List> hashMap = new HashMap<String, List>();

            optionRadio4 = "countyOption";

            if(optionRadio4.equals("cityOption")){
                List<CityBean> rsList = AreaService.getInfo(container);
                hashMap.put("cityOption",rsList);
            }
            if(optionRadio4.equals("countyOption")){
                List<CountyBean> rsList = AreaService.getInfo(container);
                hashMap.put("countyOption",rsList);
            }

            modelMap.addAttribute("areaList",hashMap);
            return "area";
        }

        return "officer";
    }

}
