package com.service.serviceimpl;

import com.bean.Leavebill;
import com.dao.LeavebillMapper;
import com.service.LeavebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeavebillServiceImpl implements LeavebillService {

    @Autowired
    private LeavebillMapper leavebillMapper;

    //查询请假单
    @Override
    public List chaxun(int userid) {
        List list = leavebillMapper.selectByUserid(userid);
        return list;
    }

    @Override
    public int updateSelective(Leavebill leavebill) {
        int i = leavebillMapper.updateByPrimaryKeySelective(leavebill);
        return i;
    }

    @Override
    public Leavebill findLeavebill(int leaveid) {
        Leavebill leavebill = leavebillMapper.selectByPrimaryKey(leaveid);
        return leavebill;
    }

    //添加请假单
    //这里使用leavebillMapper.insertSelective(leavebill)语句
    // 添加请假单会报异常,具体原因待查

    @Override
    public int addLeavebill(Leavebill leavebill) {
        int i = leavebillMapper.insert(leavebill);
        return i;
    }
}
