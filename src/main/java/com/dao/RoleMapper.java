package com.dao;

import com.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //查询所有角色
    public List<Role> selectAllRoles();

    //禁用启用
    public Integer changeState(Map map);

    //修改
    public Integer update(Map map);

    //删除中间表中角色对应的信息
    public int deletemiddlebyroleid(int roleid);

    //删除角色
    public int deleterole(int roleid);

    //根据角色id查询用户
    public int selectusers(int roleid);

}