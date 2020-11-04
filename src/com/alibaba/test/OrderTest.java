package com.alibaba.test;

import com.alibaba.dao.OrderDao;
import com.alibaba.dao.OrderItemDao;
import com.alibaba.dao.impl.OrderDaoImpl;

import com.alibaba.dao.impl.OrderItemDaoImpl;
import com.alibaba.pojo.Order;
import com.alibaba.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiFupeng
 * @version V1.0
 * @Package
 * @Title
 * @date 2020-08-14 11:39
 * @company 阿里巴巴
 */
public class OrderTest {

    @Test
    public void saveOrder(){
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("2",new Date(),new BigDecimal(100),0,3));
    }

    @Test
    public void saveOrderItem() {
        OrderItemDao ot = new OrderItemDaoImpl();
        ot.saveOrderItem(new OrderItem(4,"python",2,new BigDecimal(35.00),new BigDecimal(70.00),"3"));
    }
}
