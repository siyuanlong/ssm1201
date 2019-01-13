package com.service;

import com.bean.Menu;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface Menu2Service {
    //查找菜单信息
    public PageInfo<Menu> findAllMenu(int pageindex, int pagesize);

    //导出excel表，根据id查询菜单信息
    public List<Menu> findMenuByMids(int[] mids);

    //详情
    public List<Menu> findMenuInfoByMid(Integer mid);

    //查询所有一级菜单信息
    public List findFirst(int upmenuid);

    //查询一级菜单下有没有二级菜单
    public List findsecond(int upmenuid);

    //修改
    public int updateMenu(Menu menu);
}
