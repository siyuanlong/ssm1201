package com.controller;

import com.bean.Leavebill;
import com.bean.UserTb;
import com.service.ActivitiService;
import com.service.UsertbService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ActivitiController {

    @Autowired
    private ActivitiService activitiService;

    //添加部署
    //这里上传指的是将zip压缩包内的文件以流的形式保存到数据库表中
    @RequestMapping("/bushu/add")
    public String addActi(String processname, MultipartFile processfile){
        try {
            activitiService.addprocess(processfile.getInputStream(),processname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/bushu/getbuhsulist";
    }

    //查询部署信息
    @RequestMapping("/bushu/getbuhsulist")
    public String getbushu(ModelMap map){
        List<Deployment> deploymentlist = activitiService.getdeployment();
        List<ProcessDefinition> processdeflist = activitiService.getprocessdef();

        map.put("deploymentlist",deploymentlist);
        map.put("processdeflist",processdeflist);

        return "/bushu/list";
    }

    //删除部署
    @RequestMapping("/bushu/deleteDeploy")
    public String deleteDeployment(String did){
        activitiService.deletedeployment(did);
        return "redirect:/bushu/getbuhsulist";
    }
    //查看流程图
    @RequestMapping("/bushu/chakan")
    public void chakan(String deploymentId, String resourceName, HttpServletResponse response){
        InputStream in = activitiService.chakan(deploymentId, resourceName);
        try {
            ServletOutputStream out = response.getOutputStream();
            int len = 0;
            while((len=in.read())!=-1){
                out.write(len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //提交请假单
    //注意,使用对象leavebill来接收前端传来的id,而不能接收前端传来的对象(此时传来的值为空)
    @RequestMapping("/qingjia/tijiao")
    public String tijiao(Leavebill leavebill, HttpSession session){
        UserTb u1 = (UserTb)session.getAttribute("u1");
        //修改状态
        leavebill.setState(1);
        activitiService.tijiao(leavebill,u1);
        return "redirect:/qingjia/chaxun";
    }

    //班主任任务查询
    @RequestMapping("/renwu/getlist")
    public String chaxunrenwu(HttpSession session,ModelMap map){
        UserTb usertb = (UserTb)session.getAttribute("u1");
        Integer userid = usertb.getUserId();
        String userName = usertb.getUserName();
        List tasklist = activitiService.chaxuntask(userid);
        map.put("tasklist",tasklist);
        map.put("username",userName);
        return "/renwu/list";
    }

    @Autowired
    private UsertbService usertbService;

    //办理任务回显数据
    @RequestMapping("/renwu/banli")
    public String banli(String taskid,ModelMap map){
        Leavebill leavebill =
                activitiService.findLeavebillBytaskid(taskid);

        UserTb ut =
                usertbService.selectByPrimaryKey(leavebill.getUserid());

        //获取输出线的信息
        List outlinelist = activitiService.getoutline(taskid);

        //通过任务id得到历史批注信息
        List comments = activitiService.getcomments(taskid);

        map.put("comments",comments);
        map.put("list",outlinelist);
        map.put("leavebill",leavebill);
        map.put("ut",ut);
        map.put("tid",taskid);

        return "/renwu/banli";
    }

    //班主任审批
    @RequestMapping("/renwu/shenpi")
    public String shenpi(String result,String tid,Leavebill leavebill,
                         HttpSession session,String pizhu){
        System.out.println("result="+result+" ; "+"tid="+tid);
        UserTb u1 = (UserTb)session.getAttribute("u1");
        Integer roleid = u1.getRoleId();
        activitiService.shanpi(result, tid, leavebill,u1,pizhu);
        return "redirect:/renwu/getlist";
    }
}
