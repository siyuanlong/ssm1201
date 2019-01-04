package com.controller;

import com.bean.Student;
import com.github.pagehelper.PageInfo;
import com.service.StudentsService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @RequestMapping("/Educational/student/list")
    public String stuPage(ModelMap map,
                          @RequestParam(value = "stuindex",defaultValue = "1") int pageindex,
                          String sname,String sid,String ssex){
        System.out.println(sname+" "+sid+" "+ssex);
        PageInfo<Student> stuPageInfo = studentsService.findAllStuInfo(pageindex, PageUtil.PAGESIZE, sname, sid, ssex);
        map.put("stupi",stuPageInfo);
        map.put("sname",sname);
        map.put("sid",sid);
        map.put("ssex",ssex);
        return "Educational/student/list";
    }
}
