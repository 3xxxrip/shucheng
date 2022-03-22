package com.lj.utils;

import com.lj.pojo.Book;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static <T>T copyParamToBean(T bean, Map map){
        try {
            //这行代码的作用就是获取网页传来的参数封装成map，根据键值对把值填充到JavaBean中
            BeanUtils.populate(bean,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    //将字符串转化成为int类型的转化方法
    public static int parseInt(String str,int defaultValue){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;

    }
}
