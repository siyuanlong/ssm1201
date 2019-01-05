package com.dao;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    //查询所有学生信息
    public List getAllStuInfo(Map map);

    public List<Department> stuselectalldeparts();

    public List<Major> stuselectallzy(Integer id);

    public List<Classes> selectstuclass(Integer stuzyid);
}