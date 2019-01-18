package com.service;

import com.bean.Information;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookService {
    //查询information表
    public PageInfo<Information> findInfo(String title,int pageindex,int pagesize);

    //查询资料类型
    public List findInfoType();

    //文件上传添加进数据库
    public int insert(Information information);

}
