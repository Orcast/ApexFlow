package com.apex.core.model;

import java.math.BigDecimal;

/**
 * 订单商品明细实体类，代表订单中每个商品的具体信息。
 * 记录订单中每个商品的购买数量、价格和小计金额，用于订单详情展示。
 */
public class OrderItem {
    /**
     * 订单项的唯一标识符
     */
    private Integer id;

    /**
     * 关联的订单号，用于建立订单项与订单的关联
     */
    private String orderId;

    /**
     * 商品ID，关联商品表中的具体商品
     */
    private Integer productId;

    /**
     * 商品名称（下单时的快照），记录下单时的商品名称
     */
    private String productName;

    /**
     * 购买数量，记录用户购买该商品的数量
     */
    private Integer quantity;

    /**
     * 下单时的单价，记录下单时该商品的价格
     */
    private BigDecimal price;

    /**
     * 小计金额，计算方式为数量乘以单价
     */
    private BigDecimal subtotal;

    public OrderItem() {}

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
