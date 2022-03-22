package com.lj.dao;

import com.lj.pojo.User;

public interface UserDao {
    //根据用户名查询用户消息，如果返回null则没有这个用户，注册用的
    public User queryUserByUsername(String username);

    //根据用户名和密码查询用户消息,就是登录用的，返回null说明没有这条信息
    public User queryUserByUsernameAndPassword(String username,String password);

    //保存用户信息,用于注册
    //返回-1表示操作失败，其他是语句影响的行数
    public int saveUser(User user);

}
