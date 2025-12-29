package com.apex.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品信息实体类，代表系统中的商品基本信息和库存状态。
 * 包含商品的分类、价格、库存、状态等核心信息。
 */
public class Product {
    /**
     * 商品的唯一标识符
     */
    private Integer id;

    /**
     * 商品名称，用于显示和搜索
     */
    private String name;

    /**
     * 商品分类，用于商品分类管理和展示
     */
    private String category;

    /**
     * 商品单价，记录商品的销售价格
     */
    private BigDecimal price;

    /**
     * 库存数量，记录商品的当前库存量
     */
    private Integer stock;

    /**
     * 商品状态：1-上架，0-下架
     */
    private Integer status;

    /**
     * 商品主图URL，用于前端展示商品图片
     */
    private String image;

    /**
     * 商品创建时间，记录商品信息被添加到系统的时间
     */
    private LocalDateTime createdAt;

    public Product() {}

    // Getter和Setter方法
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
