package com.controller;

import cn.com.webxml.MobileCodeWSSoap;
import com.bean.UserTb;
import com.service.UsertbService;
import com.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
public class UsertbController {

    @Autowired
    private UsertbService usertbService;

    @RequestMapping("/login")
    public String login(UserTb userTb, HttpServletResponse response, HttpSession session){
        UserTb tb = usertbService.login(userTb);

            try {
                response.setContentType("text/html;charset=utf-8");
                /*修改登录次数的方法*/
                tb.setLogincount(tb.getLogincount()+1);
                usertbService.updateByPrimaryKeySelective(tb);
                session.setAttribute("u1",tb);
                session.setAttribute("logintime",new Date());
                if (tb!=null&&tb.getRole().getRolestate()==1){
                    /*<script type='text/javascript'>没有效果*/
                    response.getWriter().write("<script>alert('登录成功');location.href='/index.jsp'</script>");
                }
                else{
                    response.getWriter().write("<script>alert('用户名或密码不正确,请重新登录');location.href='/login.jsp'</script>");
                }
            }
           catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    @RequestMapping("/getphone")
    @ResponseBody
    public Message getPhone(String phonenumber){
        ApplicationContext app = new ClassPathXmlApplicationContext("ssm.xml");
        MobileCodeWSSoap phone1 = (MobileCodeWSSoap)app.getBean("phone");
        String phoneInfo = phone1.getMobileCodeInfo(phonenumber, "");
        Message message = new Message();
        message.setStr(phoneInfo);
        return message;
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @RequestMapping("/updateinfo")
    public void updateinfo(UserTb userTb,HttpServletResponse response,HttpSession session){
        int i = usertbService.updateByPrimaryKeySelective(userTb);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if (i>0){
                UserTb ut = usertbService.selectByPrimaryKey(userTb.getUserId());
                session.setAttribute("u1",ut);
                writer.write("<script>alert('修改成功');top.location.href='/index.jsp'</script>");
            }else{
                writer.write("<script>alert('修改失败');location.href='user/MyUser.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updatepw")
    public void updatepw(UserTb userTb,HttpServletResponse response){
        int i = usertbService.updateByPrimaryKeySelective(userTb);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if (i>0){
                writer.write("<script>alert('修改成功');top.location.href='/loginout'</script>");
            }else{
                writer.write("<script>alert('修改失败');location.href='user/password.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
