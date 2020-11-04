package com.alibaba.service;

import com.alibaba.pojo.User;

/**
 * 用户业务接口
 * @author LiFupeng
 * @version V1.0
 * @Package service
 * @Title UserService
 * @date 2020/8/1、20:20
 * @company 阿里巴巴
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void registerUser(User user);

    /**
     * 用户登录
     * @param user
     * @return 返回为null,则表示登录失败,返回有值,则成功
     */
    User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在,false为可用
     */
    boolean existUser(String username);


}
