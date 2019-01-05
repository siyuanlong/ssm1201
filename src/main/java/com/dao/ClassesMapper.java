package com.dao;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ClassesMapper {
    int deleteByPrimaryKey(Integer classid);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    public List getAll(Map map);

    public List<Department> selectalldeparts();

    public List<Major> selectallzy(Integer id);

    public List<UserTb> selectallct(Integer zyid);


}