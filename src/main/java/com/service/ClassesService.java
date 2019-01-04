package com.service;

import com.bean.Classes;
import com.github.pagehelper.PageInfo;

public interface ClassesService {
    //班级管理分页查询
    public PageInfo<Classes> findAll(int pageindex, int pagesize,String cname,String dname);
}
