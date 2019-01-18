package com.dao;

import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;

import java.util.List;
import java.util.Map;

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

    //删除单列数据
    public Integer deleteByUdid(int udid);

    //查询所有菜单
    public List getmenu();

    //添加中间表信息
    public int insertmiddle(Map map);

    //导出excel表，根据id查询菜单信息
    public List selectMenuByMids(int[] mids);

    //查询所有一级菜单信息
    public List selectFirst(int upmenuid);

    //删除中间表菜单信息
    public Integer deleteMiddle(int mid);

    //根据mids查询用户数量
    public Integer selectUserCountByMids(List mids);

    //删除二级菜单
    public Integer deleteMenuByMids(List mids);

    //根据一级菜单查询二级菜单的数量
    public Integer selectSecondCount(List firstlist);

}