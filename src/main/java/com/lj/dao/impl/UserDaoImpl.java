package com.lj.dao.impl;

import com.lj.dao.UserDao;
import com.lj.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao  {
    //通过查询用户名判断是否存在该用户
    @Override
    public User queryUserByUsername(String username) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=?";

        return queryForOne(User.class,sql,username);
    }

    //查看用户名和密码是否错误
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return  queryForOne(User.class,sql,username,password);
    }
    //注册时保存用户信息
    @Override
    public int saveUser(User user) {
        String sql="insert into t_user (username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
