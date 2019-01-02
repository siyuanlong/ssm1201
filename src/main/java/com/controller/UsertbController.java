package com.controller;

import com.bean.UserTb;
import com.service.UsertbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
                if (tb!=null){
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
}
