package com.controller;

import com.bean.Menu;
import com.bean.Role;
import com.github.pagehelper.PageInfo;
import com.service.RolesService;
import com.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class RolesController {

    @Autowired
    private RolesService rolesService;

    //分页查询角色信息
    @RequestMapping("/power/role/list")
    public String allRoles(@RequestParam(value = "index",defaultValue = "1") int pageindex, ModelMap map){
        PageInfo<Role> rolepi = rolesService.findAllRoles(pageindex, Util.PAGESIZE);
        map.put("rolepi",rolepi);
        return "/power/role/list";
    }

    //禁用启用
    @RequestMapping("/power/role/changeState")
    public String roleState(Integer state, Integer rid, HttpServletResponse response){
        Integer roleState;
        if (state==1){
            roleState = Util.state02;
        }else {
            roleState = Util.state01;
        }
        Integer i = rolesService.updateState(roleState,rid);
        if (i>0){
            try {
                response.getWriter().write("<script>alert('修改成功')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/power/role/list";
    }

    //详情
    @RequestMapping("/power/role/info")
    public String getInfo(Integer rid,ModelMap map){
        //role和middle联查
        Role role1 = rolesService.getMenuInfo(rid);
        map.put("role1",role1);
        List menulist = rolesService.getMenu();
        map.put("menulist",menulist);
        return  "/power/role/info";
    }

    //修改前查询
    @RequestMapping("/power/role/getInfo")
    public String infoForUpdate(ModelMap map,Integer rid){
        //role和middle联查
        Role role2 = rolesService.getMenuInfo(rid);
        map.put("role2",role2);
        List menulist2 = rolesService.getMenu();
        map.put("menulist2",menulist2);
        return "/power/role/edit";
    }

    @RequestMapping("/power/role/edit")
    public String updateRole(Role role,int[] menuid){
        System.out.println(role.getRoleid());
        Integer update = rolesService.update(role, menuid);
        return "redirect:/power/role/list";
    }

    @RequestMapping("/power/role/deleterole")
    public String deleteRole(int rid){
        Integer roledelete = rolesService.roledelete(rid);
        return "redirect:/power/role/list";
    }
    //增加前查询
    @RequestMapping("/power/role/getMenuInfo")
    public String infoForInsert(ModelMap map,Integer rid){
        List menulist3 = rolesService.getMenu();
        map.put("menulist3",menulist3);
        return "/power/role/add";
    }

    //增加角色
    @RequestMapping("/power/role/add")
    public String addRole(Role role,int[] menuid,HttpServletResponse response){
        System.out.println("roleid------"+role.getRoleid());
        Boolean flag = rolesService.insertRoles(role, menuid);
            try {
                if (flag){
                response.getWriter().write("<script>alert('增加成功')</script>");
                }else{
                    response.getWriter().write("<script>alert('增加失败')</script>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "redirect:/power/role/list";
    }
}
