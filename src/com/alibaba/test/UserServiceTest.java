package com.alibaba.test;
import com.alibaba.pojo.User;
import com.alibaba.service.UserService;
import com.alibaba.service.impl.UserServiceImpl;
import org.junit.Test;


public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    //测试用于注册,往数据库保存信息
    @Test
    public void registUser() {
        userService.registerUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
        userService.registerUser(new User(null, "abc168", "666666", "abc168@qq.com"));
    }

    //测试登录,在数据匹配用户名和密码,返回null代表失败
    @Test
    public void login() {
        System.out.println( userService.login(new User(null, "wzg168", "123456", null)) );
    }

    //测试用户名是否可用
    @Test
    public void existsUsername() {
        if (userService.existUser("wzg16888")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}