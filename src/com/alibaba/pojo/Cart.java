package com.alibaba.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象模型
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.pojo
 * @Title Cart
 * @date 2020-08-13 17:36
 * @company 阿里巴巴
 */
public class Cart {

    private Map<Integer,CartItems> items = new LinkedHashMap<Integer, CartItems>();

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItems>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItems>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItems> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItems> items) {
        this.items = items;
    }


    /**
     * 添加商品,添加前首先查看是否已经将该商品添加到购物车中,
     * 如果以添加,则将数量累加,如果没有,直接放到集合中
     */
    public void addItem(CartItems cartItems) {
         /*传入商品对象,得到商品的id,判断是否为空*/
        CartItems item = items.get(cartItems.getId());
        if (item == null) {
            // 为空直接添加到map集合中
            items.put(cartItems.getId(), cartItems);
        } else {
            // 商品不为空,将商品累加
            item.setCount(item.getCount()+1);
            // 更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clearItem() {
        items.clear();
    }

    /**
     * 根据商品的id修改购物车中商品的数量
     * @param id
     * @param count
     */
    public void updateItemCount(Integer id, Integer count) {
        //首先查看集合中是否有此商品,假如有,修改商品数量,更新总金额
        CartItems cartItems = items.get(id);
        if (cartItems != null) {
            cartItems.setCount(count); //修改数量
            cartItems.setTotalPrice(cartItems.getPrice().multiply(new BigDecimal(cartItems.getCount())));
        }
    }
}
