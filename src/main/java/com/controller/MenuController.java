package com.controller;

import com.bean.Role;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.util.Util;
import com.util.UsersPoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    private PageInfo<UserTb> usersList = null;
    @RequestMapping("/power/user/list")
    public String getUsers(@RequestParam(value = "index",defaultValue = "1") int pageindex, ModelMap map){
        usersList = menuService.selectAllUsers(pageindex, Util.PAGESIZE);
        map.put("userspi",usersList);
        return "/power/user/list";
    }
    @RequestMapping("/power/user/allrole")
    public String addUsers(ModelMap map){
        List<Role> upi = menuService.findAllRoles();
        map.put("upi",upi);
        return "/power/user/add";
    }
    @RequestMapping("/power/user/addUser")
    public String addUser(ModelMap map,UserTb userTb){
        Integer i = menuService.addUsers(userTb);
        return "redirect:/power/user/list";
    }

    //批量删除
    @RequestMapping("/power/user/deleteUser")
    public String deleteUsers(int[] ids ,ModelMap map){
        return "redirect:/power/user/list";
    }

    //导出excel
    @RequestMapping("/power/user/excel")
    public void excelExport(int[] ids , ModelMap map, HttpServletResponse response){
        List<UserTb> ulist = menuService.findUsersByUids(ids);
        String[] headers = {"序号", "账号", "姓名", "角色"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMhhHHmmss");
        String format = sdf.format(new Date());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("d://" + format + ".xls");
            UsersPoiUtil.firstRow(headers);
            UsersPoiUtil.otherRows(ulist);
            UsersPoiUtil.export(fos);
            response.getWriter().write("<script>alert('导出成功');location.href='/power/user/list?index="+usersList.getPageNum()+"'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteOne")
    public String deleteOneUser(int udid,ModelMap map){
        Integer delete = menuService.delete(udid);
        return "redirect:/power/user/list";
    }

    @RequestMapping("/selectOne")
    public String selectOneRole(ModelMap map,int udid){
        System.out.println("udid:"+udid);
        List<Role> roles = menuService.findAllRoles();
        map.put("roles",roles);
        map.put("id",udid);
        return "/power/user/edit";
    }

    @RequestMapping("/power/user/edit")
    public String updateUsers(ModelMap map,UserTb userTb,HttpServletResponse response){
        System.out.println("tb:"+userTb.getRoleId());
        Integer update = menuService.update(userTb);
        if (update>0){
            try {
                response.getWriter().write("<script>alert('修改成功')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/power/user/list";
    }
}
