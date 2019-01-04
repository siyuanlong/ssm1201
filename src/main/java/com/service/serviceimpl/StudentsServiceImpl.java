package com.service.serviceimpl;

import com.bean.Student;
import com.dao.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<Student> findAllStuInfo(int pageindex, int pagesize, String sname, String sid, String ssex) {
        PageHelper.startPage(pageindex,pagesize);
        HashMap map = new HashMap();
        map.put("sname",sname);
        map.put("sid",sid);
        map.put("ssex",ssex);
        List list = studentMapper.getAllStuInfo(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
