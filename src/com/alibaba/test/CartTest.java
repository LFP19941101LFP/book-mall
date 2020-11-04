package com.alibaba.test;

import com.alibaba.pojo.Cart;
import com.alibaba.pojo.CartItems;
import com.alibaba.service.OrderService;
import com.alibaba.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author LiFupeng
 * @version V1.0
 * @Package
 * @Title
 * @date 2020-08-13 18:09
 * @company 阿里巴巴
 */
public class CartTest {
    /**
     * 删除商品
     * @param id
     */
    public void deleteItem(Integer id) {

    }

    /**
     * 清空购物车
     */
    @Test
    public void clearItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItems(1,"java",2,new BigDecimal(100),new BigDecimal(120)));
        cart.addItem(new CartItems(2,"c",1,new BigDecimal(500),new BigDecimal(220)));
        cart.deleteItem(2);
        cart.clearItem();
        System.out.println(cart);
    }

    /**
     * 根据商品的id修改购物车中商品的数量
     */
    @Test
    public void updateItemCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItems(1,"java",2,new BigDecimal(100),new BigDecimal(120)));
        cart.addItem(new CartItems(2,"c",1,new BigDecimal(500),new BigDecimal(220)));
        cart.deleteItem(2);
        cart.clearItem();
        cart.addItem(new CartItems(1,"java",2,new BigDecimal(100),new BigDecimal(120)));
        cart.updateItemCount(1,20);

        System.out.println(cart);
    }

    /**
     * 添加商品,添加前首先查看是否已经将该商品添加到购物车中,
     * 如果以添加,则将数量累加,如果没有,直接放到集合中
     */
    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItems(1,"java",2,new BigDecimal(100),new BigDecimal(120)));
        cart.addItem(new CartItems(2,"c",1,new BigDecimal(500),new BigDecimal(220)));
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void createOrderTest() {
        Cart cart = new Cart();
        cart.addItem(new CartItems(1,"java",2,new BigDecimal(100),new BigDecimal(120)));
        cart.addItem(new CartItems(2,"c",1,new BigDecimal(500),new BigDecimal(220)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号:" + orderService.createOrder(cart,1));
    }
}

