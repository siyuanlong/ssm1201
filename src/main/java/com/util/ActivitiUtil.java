package com.util;

import com.bean.UserTb;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class ActivitiUtil implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        //得到session中用户id
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes)(RequestContextHolder.getRequestAttributes());
        HttpSession session = requestAttributes.getRequest().getSession();
        UserTb u1 = (UserTb)session.getAttribute("u1");
        Integer userid = u1.getUserId();

        //调用sql
        if(u1.getRole().getRolename().equals("学生")){
            delegateTask.setAssignee(u1.getTeacherid()+"");
        }else if (u1.getRole().getRolename().equals("班主任")){
            //user_tb表中managerid表示该角色的上级角色的id
            delegateTask.setAssignee(u1.getManagerid()+"");
        }
    }
}
