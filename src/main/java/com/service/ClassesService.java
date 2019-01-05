package com.service;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClassesService {
    //班级管理分页查询
    public PageInfo<Classes> findAll(int pageindex, int pagesize,String cname,String dname);

    //添加班级
    public Integer insertClass(Classes classes);

    //查询学院
    public List<Department> finddeparts();

    //查询专业
    public List<Major> findzy(Integer id);

    //查询班主任
    public List<UserTb> findct(Integer zyid);
}
