package com.dao;

import com.bean.Exam;

public interface ExamMapper {
    int deleteByPrimaryKey(Integer examid);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer examid);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
}