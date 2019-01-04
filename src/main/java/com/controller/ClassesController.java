package com.controller;

import com.bean.Classes;
import com.github.pagehelper.PageInfo;
import com.service.ClassesService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @RequestMapping("/Educational/class/list")
    public String page(ModelMap map,
                       @RequestParam(value = "index",defaultValue = "1" )
                        int pageindex,String cname,String dname){
        PageInfo pageInfo = classesService.findAll(pageindex, PageUtil.PAGESIZE,cname,dname);
        map.put("pi",pageInfo);
        //数据回显
        map.put("classname",cname);
        map.put("deptname",dname);

        return "/Educational/class/list";
    }
}
