package com.service.serviceimpl;

import com.bean.UserTb;
import com.dao.UserTbMapper;
import com.service.UsertbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsertbServiceImpl implements UsertbService {

    @Autowired
    private UserTbMapper userTbMapper;

    @Override
    public UserTb login(UserTb userTb) {

        UserTb ut = userTbMapper.selectByUserName(userTb.getUserName());
        if (ut!=null&&ut.getUserPs().equals(userTb.getUserPs())){
            return ut;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserTb record) {
        return 0;
    }
}