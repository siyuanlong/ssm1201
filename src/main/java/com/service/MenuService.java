package com.service;

import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MenuService {
    //查询所有角色信息
    public PageInfo<UserTb> selectAllUsers(int pageindex, int pagesize);

    //添加用户
    public Integer addUsers(UserTb userTb);

    //查询所有角色
    public List<Role> findAllRoles();

    //批量删除
    public void del(int[] ids);

    //查询指定id的用户信息
    public List<UserTb> findUsersByUids(int[] uids);

    //删除单列数据
    public Integer delete(int udid);

    //修改用户
    public Integer update(UserTb userTb);

}
