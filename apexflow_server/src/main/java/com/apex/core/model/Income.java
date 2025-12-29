package com.apex.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 财务收支记录实体类，代表系统中的财务流水记录。
 * 包含收入、退款等财务交易信息，用于财务统计和分析。
 */
public class Income {
    /**
     * 财务记录的唯一标识符
     */
    private Integer id;

    /**
     * 关联的订单号，用于追踪财务记录与订单的对应关系
     */
    private String orderId;

    /**
     * 财务类型：income-收入，refund-退款
     */
    private String type;

    /**
     * 交易金额，正数为收入，负数为退款
     */
    private BigDecimal amount;

    /**
     * 支付方式，如支付宝、微信支付等
     */
    private String paymentMethod;

    /**
     * 财务状态：1-待入账，2-已入账
     */
    private Integer status;

    /**
     * 交易时间，记录财务交易发生的时间
     */
    private LocalDateTime transactionTime;

    /**
     * 备注信息，记录交易的附加说明
     */
    private String remark;

    public Income() {}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
