package com.apex.core.model;

import java.time.LocalDateTime;

/**
 * 物流信息实体类，代表订单的物流运输信息。
 * 包含快递公司、运单号、物流状态以及发货和收货的地址信息。
 */
public class Logistics {
    /**
     * 物流信息的唯一标识符
     */
    private Integer id;

    /**
     * 关联的订单号，与订单表建立一对一关系
     */
    private String orderId;

    /**
     * 快递公司名称，如顺丰、京东物流等
     */
    private String expressCompany;

    /**
     * 运单号，用于查询物流轨迹
     */
    private String trackingNumber;

    /**
     * 物流状态：pending-待发货，shipped-已发货，delivered-已送达
     */
    private String status;

    /**
     * 发货地址，记录商品发出的详细地址
     */
    private String senderAddress;

    /**
     * 收货地址，记录商品送达的详细地址
     */
    private String receiverAddress;

    /**
     * 发货时间，记录商品实际发出的时间
     */
    private LocalDateTime shippedAt;

    /**
     * 送达时间，记录商品送达收货人的时间
     */
    private LocalDateTime deliveredAt;

    /**
     * 物流信息创建时间，记录物流信息被创建的时间
     */
    private LocalDateTime createdAt;

    public Logistics() {}

    // Getter和Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
