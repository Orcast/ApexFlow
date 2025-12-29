package com.apex.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单主实体类，代表系统中的核心订单信息。
 * 包含订单的基本信息、状态、支付方式以及各个时间节点。
 */
public class OrderInfo {
    /**
     * 订单号，格式如ORDER20231215001，作为订单的唯一标识符
     */
    private String id;

    /**
     * 下单用户ID，关联用户表中的用户
     */
    private Integer userId;

    /**
     * 订单总金额，包含所有商品的总价
     */
    private BigDecimal totalAmount;

    /**
     * 订单状态：1-待支付，2-已支付，3-已发货，4-已完成，5-已取消
     */
    private Integer status;

    /**
     * 支付方式：alipay,wxpay等
     */
    private String paymentMethod;

    /**
     * 收货地址ID，关联用户地址表中的地址
     */
    private Integer addressId;

    /**
     * 订单创建时间，记录用户下单的时间
     */
    private LocalDateTime createdAt;

    /**
     * 支付时间，记录用户完成支付的时间
     */
    private LocalDateTime paidAt;

    /**
     * 发货时间，记录商家发货的时间
     */
    private LocalDateTime shippedAt;

    /**
     * 订单完成时间，记录订单最终完成的时间
     */
    private LocalDateTime completedAt;

    public OrderInfo() {}

    // Getter和Setter方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}