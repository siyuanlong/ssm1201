package com.service;

import com.bean.Leavebill;
import com.bean.UserTb;

import java.io.InputStream;
import java.util.List;

public interface ActivitiService {
    //新增部署文件
    public int addprocess(InputStream inputStream,String name);

    //查询部署信息
    public List getdeployment();

    //查询部署定义
    public List getprocessdef();

    //删除部署
    public int deletedeployment(String deploymentid);

    //查看流程图
    public InputStream chakan(String did,String rname);

    //提交请假单
    public int tijiao(Leavebill leavebill, UserTb userTb);

    //查询任务单
    public List chaxuntask(int userid);

    //查询请求单
    public Leavebill findLeavebillBytaskid(String taskid);

    //班主任审批
    public void shanpi(String result,String tid,Leavebill leavebill,UserTb u1,String pizhu);

    //获取所有输出线的信息
    public List getoutline(String taskid);

    //获得历史批注信息
    public List getcomments(String taskid);

}
