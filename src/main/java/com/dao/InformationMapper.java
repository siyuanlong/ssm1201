package com.dao;

import com.bean.Information;

import java.util.List;
import java.util.Map;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer informationid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    //查询所有资料信息
    public List selectInfo(Map map);

    //查询资料类型
    public List selectInfoType();
}