package com.controller;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.Student;
import com.github.pagehelper.PageInfo;
import com.service.StudentsService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @RequestMapping("/Educational/student/list")
    public String stuPage(ModelMap map,
                          @RequestParam(value = "index",defaultValue = "1") int pageindex,
                          String sname,String sid,String ssex){
        PageInfo<Student> stuPageInfo = studentsService.findAllStuInfo(pageindex, PageUtil.PAGESIZE, sname, sid, ssex);
        map.put("stupi",stuPageInfo);
        map.put("sname",sname);
        map.put("sid",sid);
        map.put("ssex",ssex);
        return "Educational/student/list";
    }

    /*查询所有的学院*/
    @RequestMapping("/Educational/student/getdeparts")
    public String getdeparts(ModelMap map){
        List<Department> studeparts = studentsService.stufinddeparts();
        System.out.println("studeparts:"+studeparts);
        map.put("studlist",studeparts);
        return "/Educational/student/add";
    }

    /*查询学院对应的专业*/
    @RequestMapping("/getstuzhuanye")
    @ResponseBody
    public List<Major> getzy(Integer stuxyid){
        List<Major> zy01 = studentsService.stufindzy(stuxyid);
        return zy01;
    }
    /*根据专业查询班级*/
    @RequestMapping("/getstubj")
    @ResponseBody
    public List<Classes> getstubj(Integer stuzyid){
        List<Classes> bj = studentsService.stufindclass(stuzyid);
        System.out.println(bj);
        return bj;
    }

    @RequestMapping("/addStu")
    public String addstu(ModelMap map,Student student){
        Integer i = studentsService.insertStu(student);
        return "redirect:/Educational/student/list";
    }
}
