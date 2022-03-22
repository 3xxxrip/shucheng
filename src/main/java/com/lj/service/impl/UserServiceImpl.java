package com.lj.service.impl;

import com.lj.dao.UserDao;
import com.lj.dao.impl.UserDaoImpl;
import com.lj.pojo.User;
import com.lj.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
    if(userDao.queryUserByUsername(username)==null){
        //没查到用户名，表示用户名可用
        return false;
    }
        return true;
    }
}
