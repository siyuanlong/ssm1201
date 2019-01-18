package com.service.serviceimpl;


import com.bean.Leavebill;
import com.bean.UserTb;
import com.service.ActivitiService;
import com.service.LeavebillService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public int addprocess(InputStream inputStream, String name) {
        Deployment deploy = repositoryService
                .createDeployment()
                .addZipInputStream(new ZipInputStream(inputStream))
                .name(name)
                .deploy();
        return 1;
    }

    @Override
    public List getdeployment() {
        return repositoryService.createDeploymentQuery().list();
    }

    @Override
    public List getprocessdef() {
        return repositoryService.createProcessDefinitionQuery().list();
    }

    @Override
    public int deletedeployment(String deploymentid) {
        repositoryService.deleteDeployment(deploymentid);
        return 1;
    }

    @Override
    public InputStream chakan(String did,String rname) {
        //查询的是bytearray这张表,参数对应里面的deploymentid和name列
        return repositoryService.getResourceAsStream(did,rname);
    }

    @Autowired
    private LeavebillService leavebillService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public int tijiao(Leavebill leavebill, UserTb userTb) {
        //更改状态
        int i = leavebillService.updateSelective(leavebill);

        //启动流程实例
        String processDefinitionKey = "Leavebill";  //Leavebill对应bpmn图中的id
        String busessKey =processDefinitionKey+"."+leavebill.getId();  //Leavebill.12的形式
        //启动实实例前使用map将用户名存到"uname"变量中(bpmn图中使用的是#{uname}),
        // 然后把map作为runtimeService.startProcessInstanceByKey的参数传进去
        Map map = new HashMap();
        map.put("uname",userTb.getUserName());

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(processDefinitionKey, busessKey, map);
        //根据流程实例id查询task对象,目的是获得完成学生任务所需要的id
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();

        //完成学生任务
        taskService.complete(task.getId());
        return 1;
    }

    @Override
    public List chaxuntask(int userid) {
        List<Task> list = taskService
                .createTaskQuery()
                .taskAssignee(userid + "")
                .list();
        return list;
    }

    //查询请求单
    @Override
    public Leavebill findLeavebillBytaskid(String taskid) {
        //获取task对象
        Task task = taskService
                .createTaskQuery()
                .taskId(taskid)
                .singleResult();
/*
*
* 多看几遍，不怎么命明白
*
* */
        //根据实例定义id获得实例对象
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();

        //获得businessKey
        String businessKey = instance.getBusinessKey();

        //获得请求单对应的请求id
        String leaveid = businessKey.substring(businessKey.lastIndexOf(".") + 1);

        //根据请求id查询请求单
        Leavebill leavebill = leavebillService.findLeavebill(Integer.parseInt(leaveid));

        return leavebill;
    }

    @Override
    public void shanpi(String result,String tid,Leavebill leavebill,UserTb u1,String pizhu) {

        //添加批注信息
        Task task =
                taskService.createTaskQuery().taskId(tid).singleResult();
        Authentication.setAuthenticatedUserId(u1.getUserName());

        String processInstanceId = task.getProcessInstanceId();
        taskService.addComment(tid,processInstanceId,pizhu);

        //先对result进行判断
            Map map = new HashMap();
            map.put("mess",result);
            taskService.complete(tid,map);

        //班主任主管(即最后一个环节在通过后,请求单状态需要改变)
        if (result.equals("通过")){
            //修改请求单状态
            ProcessInstance instance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();

            if (instance==null){

                leavebill.setState(2);
                leavebillService.updateSelective(leavebill);
            }
        }

        if(result.equals("不通过")){
             //修改请求单状态
            leavebill.setState(3);
            leavebillService.updateSelective(leavebill);
        }
    }

    @Override
    public List getoutline(String taskid) {
        //根据任务id获得task对象
        Task task =
                taskService.createTaskQuery().taskId(taskid).singleResult();
        //得到部署定义id
        String definitionId = task.getProcessDefinitionId();
        //根据流程定义id得到流程定义的实体对象
        ProcessDefinitionEntity definitionEntity
                = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(definitionId);

        String instanceId = task.getProcessInstanceId();
        //根据流程实例id获得查询流程实例
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        //获得当前活动的节点
        String activityId = processInstance.getActivityId();

        //从ProcessDefinitionEntity中查询和当前活动id相同的元素
        List<ActivityImpl> activities = definitionEntity.getActivities();

        ArrayList names = new ArrayList();
        for (ActivityImpl activity : activities) {
            if (activity.getId().equals(activityId)){
                //当前活动节点的输出线对象
                List<PvmTransition> transitions = activity.getOutgoingTransitions();
                for (PvmTransition transition : transitions) {
                    Object name = transition.getProperty("name");
                    names.add(name);
                }
            }
        }
        return names;
    }

    @Autowired
    private HistoryService historyService;

    //获得历史批注信息
    @Override
    public List getcomments(String taskid) {
        //通过任务id查询流程实例对象
        Task task =
                taskService.createTaskQuery().taskId(taskid).singleResult();
        String instanceId = task.getProcessInstanceId();
        List<HistoricTaskInstance> list =
                historyService.createHistoricTaskInstanceQuery().processInstanceId(instanceId).list();

        List list1 = new ArrayList();

        for (HistoricTaskInstance taskInstance : list) {
            List<Comment> taskComments = taskService.getTaskComments(taskInstance.getId());
            list1.add(taskComments);
        }
        return list1;
    }
}
