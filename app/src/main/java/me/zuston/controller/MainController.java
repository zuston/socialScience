package me.zuston.controller;

import me.zuston.bean.CorruptBean;
import me.zuston.service.CorruptService;
import me.zuston.service.OfficerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

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
                         @RequestParam("optionRadio3")String optionRadio4,
                         ModelMap modelMap){

        HashMap<String,String> container = new HashMap<String, String>();
        container.put("searchContent",content);
        container.put("select",select);
        container.put("radio1",optionRadio1);
        container.put("radio2",optionRadio2);
        container.put("radio3",optionRadio3);
        container.put("radio4",optionRadio4);

        if(select.equals("corrupt")){
            List<CorruptBean> corruptBeenList = CorruptService.getInfo(container);
            modelMap.addAttribute("corruptBeenList",corruptBeenList);
        }

        if(select.equals("officer")){
            HashMap<String,List> officerHashMap = (HashMap<String, List>) OfficerService.getInfo(container);
            modelMap.addAttribute("officerMap",officerHashMap);
        }

        if(select.equals("area")){

        }

        return "show";
    }

}
