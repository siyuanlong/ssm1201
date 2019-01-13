package com.service.serviceimpl;

import com.bean.Menu;
import com.dao.MenuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.Menu2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Menu2ServiceImpl implements Menu2Service {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageInfo<Menu> findAllMenu(int pageindex,int pagesize) {
        PageHelper.startPage(pageindex,pagesize);
        List list = menuMapper.getmenu();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Menu> findMenuByMids(int[] mids) {
        List list = menuMapper.selectMenuByMids(mids);
        return list;
    }

    @Override
    public List<Menu> findMenuInfoByMid(Integer mid) {
        List menuList = new ArrayList();
        Menu menu01 = menuMapper.selectByPrimaryKey(mid);
        menuList.add(menu01);
        if (menu01.getUpmenuid()!=-1){
            Menu menu02 = menuMapper.selectByPrimaryKey(menu01.getUpmenuid());
            menuList.add(menu02);
        }
        return menuList;
    }
    @Override
    public List findFirst(int upmenuid) {
        List list = menuMapper.selectFirst(upmenuid);
        return list;
    }

    @Override
    public List findsecond(int upmenuid) {
        List secondlist = menuMapper.selectFirst(upmenuid);
        return secondlist;
    }

    @Override
    public int updateMenu(Menu menu) {
        int i = menuMapper.updateByPrimaryKeySelective(menu);
        return i;
    }
}
