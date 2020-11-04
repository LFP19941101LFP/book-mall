package com.alibaba.utils;
import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

/**
 * @author LiFupeng
 * @version V1.0
 * @Package
 * @Title
 * @date 2020-08-09 18:44
 * @company 阿里巴巴
 */
public class WebUtils {
    public static <T> T copyParameter(Map value, T bean) {
        try {
         //   System.out.println("注入之前" + bean);
            //使用BeaUtils封装所有的请求参数
            BeanUtils.populate(bean, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转化为Int
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
           // e.printStackTrace();不注掉打印数据类型转换异常
        }
        return defaultValue;
    }
}
