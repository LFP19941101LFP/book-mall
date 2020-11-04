package com.alibaba.service;

import com.alibaba.pojo.Cart;
import com.alibaba.pojo.Order;

/**
 * 订单业务接口
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.service
 * @Title OrderService
 * @date 2020-08-14 12:13
 * @company 阿里巴巴
 */
public interface OrderService {

    /**
     * 生成订单
     * @param cart
     * @param userId
     * @return
     */
    String createOrder(Cart cart, Integer userId);
}
