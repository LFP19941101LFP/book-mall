package com.alibaba.pojo;

import java.math.BigDecimal;

/**
 * 购物车商品对象类,将每个商品(图书)抽取成一个独立的对象,
 * 在剥离商品的编号,名称,数量等
 * @author LiFupeng
 * @version V1.0
 * @Package om.alibaba.pojo
 * @Title CartItems
 * @date 2020-08-13 17:31
 * @company 阿里巴巴
 */
public class CartItems {
    /**
     * 商品编号
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品数量
     */
    private Integer count;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

    public CartItems() {
    }

    public CartItems(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
