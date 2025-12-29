package com.apex.core.model;

import java.time.LocalDateTime;

/**
 * 库存变更日志实体类，记录商品库存的变化历史。
 * 包含库存变更的类型、数量、前后库存状态等信息，用于库存追踪和审计。
 */
public class InventoryLog {
    /**
     * 库存变更记录的唯一标识符
     */
    private Integer id;

    /**
     * 关联的商品ID，用于标识哪个商品发生了库存变更
     */
    private Integer productId;

    /**
     * 变更类型：sale-销售，purchase-采购，adjust-调整
     */
    private String changeType;

    /**
     * 变更数量，正数表示增加库存，负数表示减少库存
     */
    private Integer quantity;

    /**
     * 变更前的库存数量
     */
    private Integer beforeStock;

    /**
     * 变更后的库存数量
     */
    private Integer afterStock;

    /**
     * 关联的订单号，如果是销售类型的变更，记录对应的订单号
     */
    private String orderId;

    /**
     * 变更时间，记录库存变更发生的时间
     */
    private LocalDateTime createdAt;

    public InventoryLog() {}

    // Getter和Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBeforeStock() {
        return beforeStock;
    }

    public void setBeforeStock(Integer beforeStock) {
        this.beforeStock = beforeStock;
    }

    public Integer getAfterStock() {
        return afterStock;
    }

    public void setAfterStock(Integer afterStock) {
        this.afterStock = afterStock;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
