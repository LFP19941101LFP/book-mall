package com.alibaba.dao.impl;

import com.alibaba.dao.BaseDao;
import com.alibaba.dao.OrderDao;
import com.alibaba.pojo.Order;

/**
 * 订单数据持久化接口实现类
 * @author LiFupeng
 * @version V1.0
 * @Package
 * @Title
 * @date 2020-08-14 10:59
 * @company 阿里巴巴
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    /**
     * 保存订单
     * @param order
     * @return
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO t_order (order_id,create_time,price,status,user_id) VALUES (?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateDate(), order.getPrice(), order.getStatus(), order.getUserId()) ;
    }
}
