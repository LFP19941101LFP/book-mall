package com.alibaba.dao.impl;

import com.alibaba.dao.BaseDao;
import com.alibaba.dao.OrderItemDao;
import com.alibaba.pojo.OrderItem;

/**
 * 订单项数据持久化接口实现类
 * @author LiFupeng
 * @version V1.0
 * @Package
 * @Title
 * @date 2020-08-14 11:00
 * @company 阿里巴巴
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {

        String sql = "INSERT INTO t_order_item (id,name,count,price,total_price,order_id) VALUES (?,?,?,?,?,?)";
        return  update(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(),
                    orderItem.getPrice() ,orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
