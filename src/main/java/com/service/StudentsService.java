package com.service;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentsService {
    public PageInfo<Student> findAllStuInfo(int pageindex,int pagesize,String sname,String sid,String ssex);

    //查询学院
    public List<Department> stufinddeparts();

    //查询专业
    public List<Major> stufindzy(Integer id);

    //查询班级
    public List<Classes> stufindclass(Integer stuzyid);

    //添加学生
    public Integer insertStu(Student student);
}
