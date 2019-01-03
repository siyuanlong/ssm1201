package com.dao;

import com.bean.Leavebill;

public interface LeavebillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leavebill record);

    int insertSelective(Leavebill record);

    Leavebill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leavebill record);

    int updateByPrimaryKey(Leavebill record);
}