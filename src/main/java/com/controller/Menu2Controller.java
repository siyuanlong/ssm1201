package com.controller;

import com.bean.Menu;
import com.github.pagehelper.PageInfo;
import com.service.Menu2Service;
import com.util.MenusPoiUtil;
import com.util.Util;
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
public class Menu2Controller {
    private PageInfo<Menu> menupi;
    @Autowired
    private Menu2Service menu2Service;
    //查询
    @RequestMapping("/power/menu/getmenus")
    public String getMenu(ModelMap map,@RequestParam(value = "index" , defaultValue = "1") int pageindex){
        menupi = menu2Service.findAllMenu(pageindex, Util.PAGESIZE);
        map.put("menupi",menupi);
        return "/power/menu/list";
    }

    //导出excel
    @RequestMapping("/power/menu/export")
    public void excelExport(int[] mids, HttpServletResponse response){
        System.out.println("前端传来数据:"+mids);
        List<Menu> menus = menu2Service.findMenuByMids(mids);

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYmmHHhhMMss");
        String format = sdf.format(new Date());
        String[] headers = {"菜单号", "菜单名称", "路径", "菜单状态"};
        try {
            FileOutputStream fos = new FileOutputStream("d://" + format + ".xls");
            MenusPoiUtil.firstRow(headers);
            MenusPoiUtil.otherRows(menus);
            MenusPoiUtil.export(fos);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('导出成功');location.href='/power/menu/getmenus?index="+menupi.getPageNum()+"'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //详情
    @RequestMapping("/power/menu/info")
        public String getInfo(Integer mid,ModelMap map){
        List<Menu> menuList = menu2Service.findMenuInfoByMid(mid);
        if (menuList.size()==1){
            map.put("menu01",menuList.get(0));
        }else{
            map.put("menu01",menuList.get(0));
            map.put("menu02",menuList.get(1));
        }
        return "/power/menu/info";
    }
    //修改前查询
    @RequestMapping("/power/menu/geteditinfo")
    public String geteditinfo(int mid,ModelMap map){
        List firstList = menu2Service.findFirst(Util.upmenuid);
        List<Menu> menulist = menu2Service.findMenuInfoByMid(mid);
        List secondlist = menu2Service.findsecond(mid);
        map.put("firstList",firstList);
        map.put("thismenu",menulist.get(0));
        map.put("secondlistsize",secondlist.size());
        return "/power/menu/edit";
    }
    //修改
    @RequestMapping("/power/menu/edit")
    public String edit(Menu menu,HttpServletResponse response){
        int i = menu2Service.updateMenu(menu);
        if(i>0){
            try {
                response.getWriter().write("<script>alert('修改成功')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/power/menu/getmenus";
    }
}
