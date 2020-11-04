package com.alibaba.service.impl;

import com.alibaba.dao.BookDao;
import com.alibaba.dao.OrderDao;
import com.alibaba.dao.OrderItemDao;
import com.alibaba.dao.impl.BookDaoImpl;
import com.alibaba.dao.impl.OrderDaoImpl;
import com.alibaba.dao.impl.OrderItemDaoImpl;
import com.alibaba.pojo.*;
import com.alibaba.service.OrderService;
import java.util.Date;
import java.util.Map;

/**
 * 订单业务实现类
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.service.impl
 * @Title Order ServiceImpl
 * @date 2020-08-14 12:16
 * @company 阿里巴巴
 */
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    /**
     * 创建订单项
     * @param cart
     * @param userId
     * @return
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单号
        String orderId = System.currentTimeMillis()+""+userId;
        //创建订单对象
        Order order = new Order(orderId, new Date(),cart.getTotalPrice(), 0,userId);
        //保存订单
        orderDao.saveOrder(order);

        //int i = 12 / 0;      //测试事务回滚

        //遍历购物车中的每一个商品项转换为订单保存到数据库
        for (Map.Entry<Integer , CartItems> entry : cart.getItems().entrySet()) {
            CartItems  cartItems = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItems.getName(), cartItems.getCount(), cartItems.getPrice(), cartItems.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            // 更新结账后减库存
            Book book = bookDao.getBookById(cartItems.getId());
            // 结账后销量加高
            book.setSales(book.getSales() + cartItems.getCount());
            // 结账后库存减
            book.setStock(book.getStock() - cartItems.getCount());
            bookDao.updateBook(book);
        }
        cart.clearItem();
        return orderId;
    }
}
