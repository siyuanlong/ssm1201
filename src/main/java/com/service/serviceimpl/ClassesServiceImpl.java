package com.service.serviceimpl;

import com.bean.Classes;
import com.dao.ClassesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public PageInfo<Classes> findAll(int pageindex,int pagesize,String cname,String dname) {
        PageHelper.startPage(pageindex,pagesize);

        //因为mybatis传参只允许一个参数，
        // 所以将参数cname，dname放进map一起传进去
        Map map = new HashMap();
        map.put("cname",cname);
        map.put("dname",dname);
        List list = classesMapper.getAll(map);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
