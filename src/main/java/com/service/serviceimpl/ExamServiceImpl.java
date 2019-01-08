package com.service.serviceimpl;

import com.bean.Exam;
import com.dao.ExamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public PageInfo<Exam> findAllExamInfo(int pageindex, int pagesize, String subject) {
        PageHelper.startPage(pageindex,pagesize);
        HashMap map = new HashMap();
        map.put("subject",subject);
        List list = examMapper.getAllExamInfo(map);
        System.out.println("list:"+list);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List findAllDeptInfo() {
        return examMapper.getAllDeptInfo();
    }

    @Override
    public List findMajorBydid(Integer deptid) {
        return  examMapper.getMajorBydid(deptid);
    }

    @Override
    public List findClassByzid(Integer zyid) {
        return examMapper.getClassByzid(zyid);
    }

    @Override
    public int insertExam(Exam record) {
        return examMapper.insert(record);
    }
}
