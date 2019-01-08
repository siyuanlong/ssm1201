package com.service;

import com.bean.Exam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExamService {
    //查询考试信息
    public PageInfo<Exam> findAllExamInfo(int pageindex, int pagesize, String subject);

    //查询所有的学院信息
    public List findAllDeptInfo();

    //根据学院id查询专业信息
    public List findMajorBydid(Integer deptid);

    //根据专业id查询班级信息
    public List findClassByzid(Integer zyid);

    //增加考试信息
    int insertExam(Exam record);
}
