package com.lj.test;

import com.lj.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {
    @Test
    public void test(){
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
    }
}
