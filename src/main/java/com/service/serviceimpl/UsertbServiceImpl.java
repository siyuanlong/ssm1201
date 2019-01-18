package com.service.serviceimpl;

import com.bean.Classes;
import com.bean.Menu;
import com.bean.UserTb;
import com.dao.MenuMapper;
import com.dao.UserTbMapper;
import com.github.pagehelper.PageInfo;
import com.service.UsertbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsertbServiceImpl implements UsertbService {

    @Autowired
    private UserTbMapper userTbMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserTb login(UserTb userTb) {
        //1.登录前先进行user_tb和role表联查，获取到UserTb对象
        UserTb ut = userTbMapper.selectByUserName(userTb.getUserName());
        if (ut!=null&&ut.getUserPs().equals(userTb.getUserPs())){
            //2.密码比对正确后，再根据ut.getRoleId()得到roleid从而获取到menu对象的集合
            // (sql语句使用middle和menu两表联查)
            List<Menu> menus = menuMapper.selectMenuByRoleid(ut.getRoleId());
            //一级集合
            List menuList = new ArrayList();
            for (Menu menu : menus) {
                if (menu.getUpmenuid()==-1){
                    //二级集合
                    ArrayList secondList = new ArrayList();
                    for (Menu second : menus) {
                        if (second.getUpmenuid()==menu.getMenuid()){
                            secondList.add(second);
                        }
                    }
                    //先将二级集合赋值给Menu的seconds属性
                    menu.setSeconds(secondList);
                    //再将二级集合添加到一级集合中
                    menuList.add(menu);
                }
            }
            //将menu对象集合赋值给Role的menus属性
            ut.getRole().setMenus(menuList);

            if (ut.getStudentId()!=null){
                int teacherid = userTbMapper.findteacherid(ut.getUserId());
                ut.setTeacherid(teacherid);
            }
            return ut;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserTb record) {
        return userTbMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserTb selectByPrimaryKey(Integer userId) {
        return userTbMapper.selectByPrimaryKey(userId);
    }
}