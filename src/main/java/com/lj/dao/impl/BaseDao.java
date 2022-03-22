package com.lj.dao.impl;

import com.lj.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用Dbutils操作数据库
    private QueryRunner runner=new QueryRunner();

    //update方法用来执行增删改语句
    //如果返回-1就说明执行失败，如果返回其他就是返回执行sql影响的行数
    public int update(String sql,Object... args){
        Connection connection= JdbcUtils.getConnection();
        try {
            return runner.update(connection,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //手动抛出一个异常给filter后置代码捕获以便回滚事务
            throw new RuntimeException(throwables);
        }
    }

    //查询sql返回一条记录，也就是一个JavaBean对象
    /*
    type 返回的对象类型,就是people.class
    sql  执行的sql语句
    args sql对应的参数值
    <T>  返回的类型的泛型
    */
    public <T> T queryForOne(Class<T> type , String sql, Object... args){
        Connection connection= JdbcUtils.getConnection();
        try {
            //这条语句自己就有一个返回值,就是BeanHandler<T>(type)的一个对象
            return runner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //查询sql返回多条记录，多个JavaBean对象
     /*
    type 返回的对象类型
    sql  执行的sql语句
    args sql对应的参数值
    <T>  返回的类型的泛型
    */
    public <T> List<T> queryForList(Class <T> type, String sql, Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return runner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //执行返回一行一列的sql语句
      /*
        sql  执行的sql语句
        args sql对应的参数值
       */
    public Object queryForSingleValue(String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return runner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
