package com.alibaba.dao;

import com.alibaba.pojo.OrderItem;

/**
 * 订单项数据持久化接口
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.dao
 * @Title OrderItem
 * @date 2020-08-14 10:57
 * @company 阿里巴巴
 */
public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem
     * @return 保存的个数
     */
    int saveOrderItem(OrderItem orderItem);

}
