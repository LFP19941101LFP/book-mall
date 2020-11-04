package com.alibaba.dao.impl;

import com.alibaba.dao.BaseDao;
import com.alibaba.dao.UserDao;
import com.alibaba.pojo.User;

/**
 * 用户持久层实现类
 * @author LiFupeng
 * @version V1.0
 * @Package impl
 * @Title UserDaoImpl
 * @date 2020/8/1、12:18
 * @company 阿里巴巴
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 查询用户名
     * @return 查询用户名,若返回null,则表示没有该用户
     */
    @Override
    public User getUserName(String username) {
        String sql = " SELECT id,username,password,email FROM t_user WHERE username = ? ";
        return queryForOne(User.class, sql, username);
    }

    /**
     * 查询用户名和用户密码
     * @param username 用户名
     * @param password 密码
     * @return 若返回null,则表示用户名和密码错误
     */
    @Override
    public User getUserNameAndPwd(String username, String password) {
        String sql = " SELECT id,username,password,email FROM t_user WHERE username = ? AND `password` = ? ";
        return queryForOne(User.class ,sql, username, password);
    }

    /**
     * 保存用户信息
     * @param user 用户
     * @return 返回受影响的行数,保存失败返回-1
     */
    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO  t_user (id,username,`password`,email) VALUES (?,?,?,?)";
        return update(sql,user.getId(),user.getUsername(),user.getPassword(),user.getEmail());
    }








}
