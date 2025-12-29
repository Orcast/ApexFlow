package com.apex.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 售后服务实体类，代表系统中的一个售后申请记录。
 * 包含售后单的基本信息、状态、处理进度以及关联的订单信息。
 */
public class AfterSales {
    /**
     * 售后单的唯一标识符
     */
    private Integer id;

    /**
     * 关联的订单号，用于建立售后单与订单的关联
     */
    private String orderId;

    /**
     * 售后类型：1-退货，2-换货，3-维修
     */
    private Integer type;

    /**
     * 申请原因，描述用户申请售后的具体原因
     */
    private String reason;

    /**
     * 售后状态：1-申请中，2-审核通过，3-审核拒绝，4-已完成
     */
    private Integer status;

    /**
     * 退款金额，对于退货类型的售后单有效
     */
    private BigDecimal refundAmount;

    /**
     * 申请时间，记录用户提交售后申请的时间
     */
    private LocalDateTime applyTime;

    /**
     * 处理时间，记录客服处理售后申请的时间
     */
    private LocalDateTime processTime;

    /**
     * 处理备注，记录客服处理时的备注信息
     */
    private String processRemark;

    public AfterSales() {}

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public LocalDateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    public String getProcessRemark() {
        return processRemark;
    }

    public void setProcessRemark(String processRemark) {
        this.processRemark = processRemark;
    }
}
