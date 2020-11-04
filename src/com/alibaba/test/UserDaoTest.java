package com.alibaba.test;

import com.alibaba.dao.UserDao;
import com.alibaba.dao.impl.UserDaoImpl;

import com.alibaba.pojo.User;
import org.junit.Test;

/**
 * 测试持久层sql语句是否正确
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.test
 * @Title UserDaoTest
 * @date 2020-08-02 22:16
 * @company 阿里巴巴
 */
public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();

    // 若用户名为null,则表示数据库中没有该用户,用户名可用
    @Test
    public void testGetUserName(){
        if (userDao.getUserName("lft") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    // 若返回空则表示输入的用户名和密码错误,登录失败
    @Test
    public void testGetUserNameAndPwd() {
        if (userDao.getUserNameAndPwd("lfp","456789") == null) {
            System.out.println("用户名或密码错误,登录失败!");
        } else {
            System.out.println("登陆成功");
        }
    }

    // 保存用户数据
    @Test
    public void testAddUser(){
        System.out.println(userDao.addUser(new User(null,"lfp","456789","1823255@qq.com")));
    }
}
