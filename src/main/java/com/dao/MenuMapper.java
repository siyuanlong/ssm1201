package com.dao;

import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    //menu界面
    public List<Menu> selectMenuByRoleid(int roleid);

    //查询所有角色信息
    public List<UserTb> selectAllUsers();

    //查询所有role
    public List<Role> selectAllRoles();

    //批量删除用户
    public void PlDeleteUsers(int[] ids);

    //查询所有角色信息
    public List<UserTb> selectUsersByUids(int[] uids);
}