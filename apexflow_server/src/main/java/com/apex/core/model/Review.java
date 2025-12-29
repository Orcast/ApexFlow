package com.apex.core.model;

import java.time.LocalDateTime;

/**
 * 商品评价实体类，代表用户对购买商品的评价信息。
 * 包含评价内容、评分、图片等，用于商品口碑管理和展示。
 */
public class Review {
    /**
     * 评价的唯一标识符
     */
    private Integer id;

    /**
     * 关联的订单号，用于建立评价与订单的关联
     */
    private String orderId;

    /**
     * 商品ID，标识被评价的具体商品
     */
    private Integer productId;

    /**
     * 评价用户ID，标识发表评价的用户
     */
    private Integer userId;

    /**
     * 评分：1-5星，代表用户对商品的满意度
     */
    private Integer rating;

    /**
     * 评价内容，用户对商品的文字评价
     */
    private String content;

    /**
     * 评价图片URL，多个用逗号分隔，展示用户上传的评价图片
     */
    private String images;

    /**
     * 是否匿名评价，true表示匿名，false表示公开
     */
    private Boolean isAnonymous;

    /**
     * 评价时间，记录用户发表评价的时间
     */
    private LocalDateTime createdAt;

    public Review() {}

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
