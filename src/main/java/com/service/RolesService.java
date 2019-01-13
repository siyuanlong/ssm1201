package com.service;

import com.bean.Menu;
import com.bean.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface RolesService {
    //查询角色信息
    public PageInfo<Role> findAllRoles(int pageindex, int pagesize);
    //启用、禁用
    public Integer updateState(Integer state,Integer rid);
    //详情
    public Role getMenuInfo(Integer rid);

    //获得菜单信息
    public List getMenu();

    //修改
    public Integer update(Role role,int[] ids);

    //删除
    public int roledelete(int rid);

    //增加角色
    public Boolean insertRoles(Role role,int[] ids);
}
