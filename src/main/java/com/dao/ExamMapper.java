package com.dao;

import com.bean.Exam;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
    int deleteByPrimaryKey(Integer examid);

    //增加考试信息
    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer examid);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    //查询所有的考试信息
    //注意:参数传string类型数据会报错
     public List getAllExamInfo(Map map);

    //查询所有的学院信息
    public List getAllDeptInfo();

    //根据学院id查询专业信息
    public List getMajorBydid(Integer deptid);

    //根据专业id查询班级信息
    public List getClassByzid(Integer zyid);

}