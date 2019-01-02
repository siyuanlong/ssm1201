package com.study.controller;

import com.study.beans.Student;
import com.study.service.serviceimpl.StuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class Controller01 {
    @Autowired
    private StuServiceImpl stuService;
    @RequestMapping("/test01")
    public String con(Model model){
        List<Student> studentList = stuService.ser01();
        System.out.println(studentList);
        model.addAttribute("s",studentList);
        return "success";
    }
}
