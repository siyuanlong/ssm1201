package com.study.dao.daoimpl;

import com.study.beans.Student;
import com.study.dao.StuDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sdao")
public class StuDaoImpl implements StuDao {
    /*
        SqlSessionTemplate实现了SqlSession接口，
        所以可以对数据库进行操作
    */
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public List<Student> fun01() {
        List<Student> studentList = sqlSessionTemplate.selectList("selectStuBySid");
        System.out.println("dao:"+studentList);
        return studentList;
    }
}
