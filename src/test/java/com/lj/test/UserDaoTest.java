package com.lj.test;

import com.lj.dao.UserDao;
import com.lj.dao.impl.UserDaoImpl;
import com.lj.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao=new UserDaoImpl();
        User user = userDao.queryUserByUsername("admin");
        System.out.println(user);

    }

    @Test
    public void queryUserByUsernameAndPassword() {
    }

    @Test
    public void saveUser() {
    }
}