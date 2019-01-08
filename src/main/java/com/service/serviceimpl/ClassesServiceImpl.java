package com.service.serviceimpl;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.UserTb;
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




    @Override
    public Integer insertClass(Classes classes) {
        int insert = classesMapper.insert(classes);
        return insert;
    }

    @Override
    public List<Department> finddeparts() {
        List<Department> departs = classesMapper.selectalldeparts();
        return departs;
    }

    @Override
    public List<Major> findzy(Integer id) {
        List<Major> majorList = classesMapper.selectallzy(id);
        return majorList;
    }

    @Override
    public List<UserTb> findct(Integer zyid) {
        List<UserTb> ct = classesMapper.selectallct(zyid);
        return ct;
    }

    @Override
    public List<Classes> findClassByCids(int[] cids) {
        List<Classes> classesList = classesMapper.selectClassByCids(cids);
        return classesList;
    }

    @Override
    public PageInfo<Classes> findShenheAll(int pageindex, int pagesize, Integer cid, String cname) {
        PageHelper.startPage(pageindex,pagesize);
        HashMap map = new HashMap();
        map.put("cid",cid);
        map.put("cname",cname);
        List list = classesMapper.selectshenheall(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
