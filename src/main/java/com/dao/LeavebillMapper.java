package com.dao;

import com.bean.Leavebill;

import java.util.List;

public interface LeavebillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leavebill record);

    int insertSelective(Leavebill leavebill);

    Leavebill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leavebill record);

    int updateByPrimaryKey(Leavebill record);

    public List selectByUserid(int userid);
}