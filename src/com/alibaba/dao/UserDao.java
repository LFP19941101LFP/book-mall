package com.alibaba.dao;

import com.alibaba.pojo.User;

/**
 * 用户持久层接口
 * @author LiFupeng
 * @version V1.0
 * @Package dao
 * @Title UserDao
 * @date 2020/8/1、12:13
 * @company 阿里巴巴
 */
public interface UserDao {

    /**
     *  根据用户名查询用户信息
     * @param username 查询用户名
     * @return 返回null,则没有此用户
     */
    User getUserName(String username);

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 如果返回为空,则表示用户名和密码错误
     */
    User getUserNameAndPwd(String username, String password);

    /**
     * 保存用户
     * @param user 用户
     * @return 保存成功返回受影响的行数,否则返回-1
     */
    int addUser(User user);

}
