package com.alibaba.service.impl;

import com.alibaba.dao.UserDao;
import com.alibaba.dao.impl.UserDaoImpl;
import com.alibaba.pojo.User;
import com.alibaba.service.UserService;

/**
 * 用户业务层接口实现类
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.pojo.User
 * @Title UserServiceImpl
 * @date 2020-08-03 21:57
 * @company 阿里巴巴
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 用户注册
     * @param user
     */
    @Override
    public void registerUser(User user) {
        // 调用dao层保存用户方法,将用户信息保存到数据
        userDao.addUser(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        //调用dao层查询用户名和密码,查到代表登录成功
        return userDao.getUserNameAndPwd(user.getUsername(), user.getPassword());
    }

    /**
     * 检查用户名是否可用
     * @param username
     * @return
     */
    @Override
    public boolean existUser(String username) {
        if (userDao.getUserName(username) == null) {
            //为null表示没有查到,表示用户名可用
            return false;
        }
        return true;
    }
}
