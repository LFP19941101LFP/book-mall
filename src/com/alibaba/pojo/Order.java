package com.alibaba.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单模块
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.pojo
 * @Title Order
 * @date 2020-08-14 10:48
 * @company 阿里巴巴
 */
public class Order {
    private String orderId;
    private Date createDate;
    private BigDecimal price;
    //0:未发货  1:已发货
    private Integer status = 0;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createDate, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
