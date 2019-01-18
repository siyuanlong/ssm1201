package com.controller;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Exam;
import com.bean.Major;
import com.github.pagehelper.PageInfo;
import com.service.ExamService;
import com.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ExamController {


    @Autowired
    private ExamService examService;

    @RequestMapping("/Educational/exam/exam")
    public String examPage(ModelMap map,@RequestParam(defaultValue = "5") int size,
                          @RequestParam(value = "index", defaultValue = "1") int pageindex,
                          String subject) {
        PageInfo<Exam> allExamInfo = examService.findAllExamInfo(pageindex, size, subject);
        map.put("exampi", allExamInfo);
        map.put("subject", subject);
        return "Educational/exam/exam";
}

    //查询所有的学院
    @RequestMapping("/Educational/exam/getdeparts")
    public String getdeparts(ModelMap map) {
        List<Department> examdeparts = examService.findAllDeptInfo();
        System.out.println("examdeparts:" + examdeparts);
        map.put("examdlist", examdeparts);
        return "/Educational/exam/add";
    }

    //查询学院对应的专业
    @RequestMapping("/getexamzhuanye")
    @ResponseBody
    public List<Major> getzy(Integer examxyid) {
        System.out.println("zy");
        System.out.println("examxyid:"+examxyid);
        List<Major> zy01 = examService.findMajorBydid(examxyid);
        return zy01;
    }

    //根据专业查询班级
    @RequestMapping("/getexambj")
    @ResponseBody
    public List<Classes> getstubj(Integer examzyid) {
        List<Classes> bj = examService.findClassByzid(examzyid);
        System.out.println(bj);
        return bj;
    }

    @RequestMapping("/addExam")
    public String addexam(ModelMap map, Exam exam) {
        Integer i = examService.insertExam(exam);
        return "redirect:/Educational/exam/exam";
    }
}


