package com.controller;

import com.bean.Leavebill;
import com.bean.UserTb;
import com.service.LeavebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LeavebillController {

    @Autowired
    private LeavebillService leavebillService;

    //查询请假单
    @RequestMapping("/qingjia/chaxun")
    public String chaxun(HttpSession session, ModelMap map){
        //从session域中取登录用户的userid
        UserTb user = (UserTb)session.getAttribute("u1");
        System.out.println(user.getUserName());
        Integer userId = user.getUserId();
        List leavebillList = leavebillService.chaxun(userId);
        map.put("llist",leavebillList);
        return "/qingjia/list";
    }

    //添加请假单
    @RequestMapping("/qingjia/add")
    public String addLeavebill(Leavebill leavebill){
        leavebill.setState(0);
        int i = leavebillService.addLeavebill(leavebill);
        return "redirect:/qingjia/chaxun";
    }

}
