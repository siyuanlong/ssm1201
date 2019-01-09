package com.controller;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;
import com.service.ClassesService;
import com.util.PageUtil;
import com.util.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    private PageInfo pageInfo = null;
    @RequestMapping("/Educational/class/list")
    public String page(ModelMap map,
                       @RequestParam(value = "index",defaultValue = "1" )
                        int pageindex,String cname,String dname){
        pageInfo = classesService.findAll(pageindex, PageUtil.PAGESIZE,cname,dname);
        map.put("pi",pageInfo);
        //数据回显
        map.put("classname",cname);
        map.put("deptname",dname);

        return "/Educational/class/list";
    }

    @RequestMapping("Educational/Auditing")
    public String shenhepage(ModelMap map,
                       @RequestParam(value = "index",defaultValue = "1" )
                               int pageindex,Integer cid,String cname){
        System.out.println(cid+"---"+cname);
        PageInfo<Classes> shenheAll = classesService.findShenheAll(pageindex, PageUtil.PAGESIZE, cid, cname);
        map.put("shenhepi", shenheAll);
        //数据回显
        map.put("cid",cid);
        map.put("cname",cname);
        return "Educational/Auditing";
    }

    /*查询所有的学院*/
    @RequestMapping("/Educational/class/getdeparts")
    public String getdeparts(ModelMap map){
        List<Department> departs = classesService.finddeparts();
        map.put("dlist",departs);
        return "/Educational/class/add";
    }

    /*查询学院对应的专业*/
    @RequestMapping("/getzhuanye")
    @ResponseBody
    public List<Major> getzy(Integer xyid){
        List<Major> majorList = classesService.findzy(xyid);
        return majorList;
    }

    /*查询专业对应的班主任*/
    @RequestMapping("/getct")
    @ResponseBody
    public List<UserTb> getct(Integer zyid){
        List<UserTb> ct = classesService.findct(zyid);
        System.out.println(ct);
        return ct;
    }

    /*添加班级*/
    @RequestMapping("/addClass")
    public String addclass(ModelMap map, HttpServletResponse response, Classes classes,String teacher ){
        System.out.println(teacher);
        String[] split = teacher.split("-");
        classes.setTeacherid(Integer.parseInt(split[0]));
        classes.setClassteacher(split[1]);
        classes.setClassstate("未审核");
        Integer i = classesService.insertClass(classes);
        System.out.println("hello world!");
        return "redirect:/Educational/class/list";
    }

    /*导出excel表格*/
    @RequestMapping("/daochu")
    public void excelpoi(int[] cids,HttpServletResponse response){
        try {
        List<Classes> classesList = classesService.findClassByCids(cids);
        String[] headers = {"学院名称", "班级编号", "班级名称", "班主任老师", "人数"};
        PoiUtil.firstRow(headers);
        PoiUtil.otherRows(classesList);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        FileOutputStream fileOutputStream = null;
        fileOutputStream = new FileOutputStream("d://" + format + ".xls");
        PoiUtil.export(fileOutputStream);
            response.getWriter().write("<script>alert('导出成功');location.href='/Educational/class/list?index="+pageInfo.getPageNum()+"'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
