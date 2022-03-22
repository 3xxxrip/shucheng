package com.lj.service;

import com.lj.pojo.User;

//service表示业务层，一个业务一个方法
public interface UserService {
    //注册用户
    public void registUser(User user);

    //登录
    public User login(User user);

    //检查用户名是否可用
    public boolean existsUsername(String username);

}
