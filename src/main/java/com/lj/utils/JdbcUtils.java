package com.lj.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    /**
     *用来操作数据库连接用的,这个静态代码块是用来加载数据库连接池的
     */
    private static DruidDataSource dataSource;
    //创建这个ThreadLocal的目的就是为了再保存数据到数据库时出现错误会一次性回滚，提交的数据不会出错
    private static ThreadLocal<Connection> conns=new ThreadLocal<>();
    static {
        try {

            Properties properties=new Properties();
            //读取jdbc.properties属性配置文件
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(is);

            //创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取连接
    //如果获取连接失败返回的就是一个null值，因为抛出异常了，所以connection并没有赋值
    public static Connection getConnection(){
       Connection connection=conns.get();
       if(connection==null){
           //如果此时connection还为空的话就说明还没有保存到ThreadLocal中
           try {
               connection= dataSource.getConnection();

               //保存connection到ThreadLocal中，供jdbc操作使用
               conns.set(connection);

               //设置为手动管理事务
               connection.setAutoCommit(false);

           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }
       }
       return connection;
    }
/** 
* @Description: 提交事务并且关闭connection链接
        * @Param: []
        * @return: void
        * @Author: longjian
        * @Date:20:31 2022/2/23
        */

    public static void commitAndClose(){
        Connection connection = conns.get();
        //判断连接是否为空，如果是空就说明使用过需要提交和关闭
        if(connection!=null){
            try {
                //提交事务
                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭连接
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要实行remove()否则会出错(因为tomcat服务器底层使用了线程池技术)
        conns.remove();
    }
/** 
* @Description: 回滚事务并且关闭connection连接
        * @Param: []
        * @return: void
        * @Author: longjian
        * @Date:20:32 2022/2/23
        */

    public static void rollbackAndClose(){
        Connection connection = conns.get();
        //判断连接是否为空，如果是空就说明使用过能回滚和关闭
        if(connection!=null){
            try {
                //提交事务
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭连接
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要实行remove()否则会出错(因为tomcat服务器底层使用了线程池技术)
        conns.remove();
    }
//    //关闭连接，放回数据库连接池
//    public static void close(Connection connection){
//        if(connection!=null){
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//
//    }
}
