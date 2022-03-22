package com.lj.test;

import com.lj.pojo.User;
import com.lj.service.UserService;
import com.lj.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"lj168","lj168","lj168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "lj168", "lj168", null)));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("admin"));
    }
}