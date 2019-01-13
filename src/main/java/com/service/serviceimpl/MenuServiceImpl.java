package com.service.serviceimpl;

import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;
import com.dao.MenuMapper;
import com.dao.UserTbMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserTbMapper userTbMapper;

    @Override
    public PageInfo<UserTb> selectAllUsers(int pageindex, int pagesize) {
        PageHelper.startPage(pageindex,pagesize);
        List<UserTb> list = menuMapper.selectAllUsers();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Integer addUsers(UserTb userTb) {
        int insert = userTbMapper.insert(userTb);
        return insert;
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = menuMapper.selectAllRoles();
        return roles;
    }

    @Override
    public void del(int[] ids) {
       menuMapper.PlDeleteUsers(ids);
    }

    @Override
    public List<UserTb> findUsersByUids(int[] uids) {
        List<UserTb> list = menuMapper.selectUsersByUids(uids);
        return list;
    }

    @Override
    public Integer delete(int udid) {
        Integer i = menuMapper.deleteByUdid(udid);
        return i;
    }

    @Override
    public Integer update(UserTb userTb) {
        int i = userTbMapper.updateByPrimaryKeySelective(userTb);
        return i;
    }
}
