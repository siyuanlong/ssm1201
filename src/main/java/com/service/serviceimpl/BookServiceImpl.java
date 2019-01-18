package com.service.serviceimpl;

import com.bean.Information;
import com.dao.InformationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private InformationMapper informationMapper;

    @Override
    public PageInfo<Information> findInfo(String title,int pageindex,int pagesize) {
        PageHelper.startPage(pageindex,pagesize);
        Map map = new HashMap();
        map.put("title",title);
        List list = informationMapper.selectInfo(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List findInfoType() {
        List list = informationMapper.selectInfoType();
        return list;
    }

    @Override
    public int insert(Information information) {
        int insert = informationMapper.insertSelective(information);
        return insert;
    }
}
