package com.alibaba.dao;

import com.alibaba.pojo.Order;

/**
 * 订单数据持久化接口
 * @author LiFupeng
 * @version V1.0
 * @Package om.alibaba.dao;
 * @Title OrderDao
 * @date 2020-08-14 10:55
 * @company 阿里巴巴
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return 保存的个数
     */
    int saveOrder(Order order);
}
