package me.zuston.controller;

import me.zuston.bean.CityBean;
import me.zuston.bean.CorruptBean;
import me.zuston.bean.CountyBean;
import me.zuston.service.AreaService;
import me.zuston.service.CorruptService;
import me.zuston.service.OfficerService;
import me.zuston.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping(path = "/search-corrupt",method = RequestMethod.POST)
    public String searchCorrupt(@RequestParam("searchContent")String content,
                                @RequestParam("corrupt-one")String optionRadio1,
                                @RequestParam("corrupt-two")String optionRadio2,
                                @RequestParam("corrupt-three")String optionRadio3,
                                ModelMap modelMap){

        content = content.trim();

        if (!Tool.filterContent(content)){
            modelMap.addAttribute("error","请输入查询内容");
            return "corrupt";
        }


        HashMap<String,String> container = new HashMap<String, String>();
        container.put("searchContent",content);
        container.put("radio1",optionRadio1);
        container.put("radio2",optionRadio2);
        container.put("radio3",optionRadio3);
        List<CorruptBean> corruptBeenList = CorruptService.getInfo(container);
        modelMap.addAttribute("corruptBeenList",corruptBeenList);
        return "corrupt";
    }

    @RequestMapping(path = "/search-officer",method = RequestMethod.POST)
    public String searchCorrupt(@RequestParam("searchContent")String content,
                                @RequestParam("officer-one")String optionRadio1,
                                ModelMap modelMap){

        content = content.trim();

        if (!Tool.filterContent(content)){
            modelMap.addAttribute("error","请输入查询内容");
            return "officer";
        }

        HashMap<String,String> container = new HashMap<String, String>();
        container.put("searchContent",content);
        container.put("radio1",optionRadio1);
        HashMap<String,List> officerHashMap = (HashMap<String, List>) OfficerService.getInfo(container);
        modelMap.addAttribute("officerMap",officerHashMap);
        return "officer";
    }

    @RequestMapping(path = "/search-area",method = RequestMethod.POST)
    public String searchCorrupt(@RequestParam("searchContent")String content,
                                @RequestParam("area-one")String optionRadio1,
                                @RequestParam("area-two")String optionRadio4,
                                ModelMap modelMap){

        content = content.trim();

        if (!Tool.filterContent(content)){
            modelMap.addAttribute("error","请输入查询内容");
            return "area";
        }

        HashMap<String,String> container = new HashMap<String, String>();
        container.put("searchContent",content);
        container.put("radio1",optionRadio1);
        container.put("radio4",optionRadio4);
        HashMap<String,List> hashMap = new HashMap<String, List>();

        if(optionRadio1.equals("cityOption")){
            List<CityBean> rsList = AreaService.getInfo(container);
            hashMap.put("cityOption",rsList);
            hashMap.put("countyOption",null);

        }
        if(optionRadio1.equals("areaOption")){
            List<CountyBean> rsList = AreaService.getInfo(container);
            hashMap.put("countyOption",rsList);
            hashMap.put("cityOption",null);

        }

        modelMap.addAttribute("areaList",hashMap);
        return "area";
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
//        content = "522630";
//        optionRadio1 = "code";
//        optionRadio4 = "countyOption";
//        select = "area";

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
