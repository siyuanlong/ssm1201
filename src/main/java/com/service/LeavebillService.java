package com.service;

import com.bean.Leavebill;

import java.util.List;

public interface LeavebillService {
    //添加请假单
    public int addLeavebill(Leavebill leavebill);

    //查询请假单
    public List chaxun(int userid);

    public int updateSelective(Leavebill leavebill);

    //根据请求单id查询请求单
    public Leavebill findLeavebill(int leaveid);

}
