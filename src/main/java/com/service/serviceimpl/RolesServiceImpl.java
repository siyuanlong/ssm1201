package com.service.serviceimpl;

import com.bean.Menu;
import com.bean.Role;
import com.dao.MenuMapper;
import com.dao.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;
  //查询所有角色信息
    @Override
    public PageInfo<Role> findAllRoles(int pageindex, int pagesize) {
        PageHelper.startPage(pageindex,pagesize);
        List<Role> list = roleMapper.selectAllRoles();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    //禁用启用
    @Override
    public Integer updateState(Integer state,Integer rid) {
        Map map = new HashMap();
        map.put("state",state);
        map.put("rid",rid);
        Integer i = roleMapper.changeState(map);
        return i;
    }

    //详情
    @Override
    public Role getMenuInfo(Integer rid) {
        Role role = roleMapper.selectByPrimaryKey(rid);
        return role;
    }

    //查询一二级菜单
    @Override
    public List getMenu() {
        List<Menu> menus = menuMapper.getmenu();
        //一级集合
        List menuList = new ArrayList();
        for (Menu menu : menus) {
            if (menu.getUpmenuid()==-1){
                //二级集合
                ArrayList secondList = new ArrayList();
                for (Menu secondmenu : menus) {
                    if (secondmenu.getUpmenuid()==menu.getMenuid()){
                        secondList.add(secondmenu);
                    }
                }
                //先将二级集合赋值给Menu的seconds属性
                menu.setSeconds(secondList);
                //再将二级集合添加到一级集合中
                menuList.add(menu);
            }
        }
        return menuList;
    }

    //修改角色
    @Override
    @Transactional
    public Integer update(Role role, int[] ids) {
        //1.修改角色的信息
        roleMapper.updateByPrimaryKeySelective(role);

        //2.修改角色菜单列表(先删后加)
        //删除中间表中角色所对应的信息
        roleMapper.deletemiddlebyroleid(role.getRoleid());

        //middle表中根据ids添加信息
        HashMap map = new HashMap();
        map.put("rid",role.getRoleid());
        map.put("menuids",ids);
        int insertmiddle = menuMapper.insertmiddle(map);
        return insertmiddle;
    }

    @Override
    public int roledelete(int rid) {
        //先查询用户表
        int selectusers = roleMapper.selectusers(rid);
        if (selectusers>0){
            return 0;
        }else {
            int deletemiddlebyroleid = roleMapper.deletemiddlebyroleid(rid);
            int deleterole = roleMapper.deleterole(rid);
            return deleterole;
        }
    }

    @Override
    public Boolean insertRoles(Role role, int[] ids) {

        int insertRole = roleMapper.insertSelective(role);

        //middle表中根据ids添加信息
        HashMap map01 = new HashMap();
        map01.put("rid",role.getRoleid());
        map01.put("menuids",ids);
        int insertMiddle = menuMapper.insertmiddle(map01);

        if (insertRole>0&&insertMiddle>0){
            return true;
        }
        return false;
    }
}
