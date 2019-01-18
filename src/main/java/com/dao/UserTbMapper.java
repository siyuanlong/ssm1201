package com.dao;

import com.bean.UserTb;

public interface UserTbMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserTb record);

    int insertSelective(UserTb record);

    UserTb selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);
    /*
        自定义方法
    */
    public UserTb selectByUserName(String userName);

    //根据学生id查询老师id
    public int findteacherid(int userid);

}