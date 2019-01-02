package com.service;

import com.bean.UserTb;

public interface UsertbService {
    public UserTb login(UserTb userTb);
    int updateByPrimaryKeySelective(UserTb record);
}
