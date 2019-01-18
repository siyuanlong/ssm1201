package com.service;

import com.bean.Classes;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UsertbService {
    //登录
    public UserTb login(UserTb userTb);

    //根据主键动态修改
    int updateByPrimaryKeySelective(UserTb record);

    //根据主键查询
    public UserTb selectByPrimaryKey(Integer userId);

}
