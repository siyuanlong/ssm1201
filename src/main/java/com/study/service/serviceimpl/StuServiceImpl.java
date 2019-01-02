package com.study.service.serviceimpl;

import com.study.beans.Student;
import com.study.dao.daoimpl.StuDaoImpl;
import com.study.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sservice")
public class StuServiceImpl implements StuService {
    @Autowired
    private StuDaoImpl stuDao;
    @Override
    public List<Student> ser01() {
        return stuDao.fun01();
    }
}
