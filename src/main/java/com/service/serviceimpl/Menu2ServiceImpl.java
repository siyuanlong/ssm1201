package com.service.serviceimpl;

import com.bean.Menu;
import com.dao.MenuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.Menu2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

@Service
public class Menu2ServiceImpl implements Menu2Service {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageInfo<Menu> findAllMenu(int pageindex, int pagesize) {
        PageHelper.startPage(pageindex, pagesize);
        List list = menuMapper.getmenu();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Menu> findMenuByMids(int[] mids) {
        List list = menuMapper.selectMenuByMids(mids);
        return list;
    }

    @Override
    public List<Menu> findMenuInfoByMid(Integer mid) {
        List menuList = new ArrayList();
        Menu menu01 = menuMapper.selectByPrimaryKey(mid);
        menuList.add(menu01);
        if (menu01.getUpmenuid() != -1) {
            Menu menu02 = menuMapper.selectByPrimaryKey(menu01.getUpmenuid());
            menuList.add(menu02);
        }
        return menuList;
    }

    @Override
    public List findFirst(int upmenuid) {
        List list = menuMapper.selectFirst(upmenuid);
        return list;
    }

    @Override
    public List findsecond(int upmenuid) {
        List secondlist = menuMapper.selectFirst(upmenuid);
        return secondlist;
    }

    @Override
    public int updateMenu(Menu menu) {
        int i = menuMapper.updateByPrimaryKeySelective(menu);
        return i;
    }

    @Override
    public Integer addMenus(Menu menu) {
        int i = menuMapper.insertSelective(menu);
        return i;
    }

    @Override
    @Transactional
    public int deleteMenus(int mid, int upmid) {
        if (upmid != -1) {
            menuMapper.deleteMiddle(mid);
            menuMapper.deleteByPrimaryKey(mid);
            return 1;
        } else {
            List menuList = menuMapper.selectFirst(mid);
            if (menuList.size() == 0) {
                menuMapper.deleteMiddle(mid);
                //System.out.println(1/0);
                menuMapper.deleteByPrimaryKey(mid);
                return 1;
            }
        }
        return 0;
    }

    /*自定义事物声明*/
    @Autowired
    private DataSourceTransactionManager tx;

    @Override
    public int deleteBatchMenus(String[] ids) {
        //自定义事物，每次增删改之后必须要提交
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus transaction = tx.getTransaction(definition);
        try {
            ArrayList list = new ArrayList();
            //首先判断菜单是否已被用户使用
            for (int i = 0; i < ids.length; i++) {
                String[] mids = ids[i].split("_");
                int mid = Integer.parseInt(mids[0]);
                list.add(mid);
            }
            //查询
            Integer count01 = menuMapper.selectUserCountByMids(list);
            if (count01 > 0) {
                return 0;
            } else {

                ArrayList firstList = new ArrayList();
                ArrayList secondList = new ArrayList();
                for (int i = 0; i < ids.length; i++) {
                    String[] id = ids[i].split("_");
                    int mid = Integer.parseInt(id[0]);
                    int upmid = Integer.parseInt(id[1]);
                    if (upmid == -1) {
                        firstList.add(mid);
                    } else {
                        secondList.add(mid);
                    }
                }

                //全部是二级菜单的情况,直接删
                if (secondList.size() == ids.length) {
                    menuMapper.deleteMenuByMids(secondList);
                    tx.commit(transaction);
                    return 1;
                }
                //全部是一级菜单的情况,先判断一级菜单下面有没有二级菜单，
                //没有的话，直接删
                else if (firstList.size() == ids.length) {
                    Integer count02 = menuMapper.selectSecondCount(firstList);
                    if (count02 > 0) {
                        return 0;
                    }else{
                        menuMapper.deleteMenuByMids(firstList);
                        tx.commit(transaction);
                        return 1;
                    }
                }
                //既有一级菜单又有二级菜单的情况
                else {
                    menuMapper.deleteMenuByMids(secondList);
                    Integer count03 = menuMapper.selectSecondCount(firstList);
                    if (count03 > 0) {
                        throw new Exception("");
                    } else {
                        menuMapper.deleteMenuByMids(firstList);
                    }
                }
                tx.commit(transaction);
                return 1;
            }
        } catch (Exception e) {
            //回滚:可以理解为发生异常情况下，回到之前的状态
           tx.rollback(transaction);
        }
        return 0;
    }
}
