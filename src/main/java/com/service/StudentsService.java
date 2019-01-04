package com.service;

import com.bean.Student;
import com.github.pagehelper.PageInfo;

public interface StudentsService {
    public PageInfo<Student> findAllStuInfo(int pageindex,int pagesize,String sname,String sid,String ssex);
}
