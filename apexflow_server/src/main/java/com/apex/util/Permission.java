package com.apex.util;


/**
 * Represents the different types of permissions available within the system.
 * Each permission is associated with a specific role or capability, and is
 * identified by a unique database field name and a human-readable description.
 */
public enum Permission {
    /**
     * Represents the administrative role within the system. This permission is associated with
     * the ability to perform all administrative tasks and has full access to the system.
     * The field name used in the database for this permission is "is_admin", and its description
     * is "超级管理员" which translates to "Super Administrator".
     */
    ADMIN("is_admin", "超级管理员"),
    /**
     * Represents the permission for managing orders within the system.
     * This includes functionalities such as creating, updating, and deleting orders.
     * The associated database field name is "can_manage_order", and its human-readable
     * description in Chinese is "订单管理".
     */
    ORDER_MANAGE("can_manage_order", "订单管理"),
    /**
     * Represents the permission for managing logistics.
     * This permission is associated with the field name "can_manage_logistics" and
     * has a human-readable description of "物流管理".
     */
    LOGISTICS_MANAGE("can_manage_logistics", "物流管理"),
    /**
     * Represents the permission for managing after-sales processes. This includes, but is not limited to,
     * handling returns, exchanges, and customer service inquiries post-purchase.
     * The associated database field name is "can_manage_after_sales" and the human-readable description is "售后管理".
     */
    AFTER_SALES_MANAGE("can_manage_after_sales", "售后管理"),
    /**
     * Represents the permission for managing reviews within the system.
     * This permission is associated with the ability to manage, view, and possibly modify customer reviews.
     * The field name used in the database or backend system for this permission is "can_manage_review".
     * The human-readable description of this permission is "评价管理", which translates to "Review Management" in English.
     */
    REVIEW_MANAGE("can_manage_review", "评价管理"),
    /**
     * Represents the permission for managing inventory within the system.
     * This permission is used to determine if a user has the capability to manage inventory, including
     * operations such as adding, removing, or updating inventory items.
     * The associated database field name is "can_manage_inventory" and its human-readable description is "库存管理".
     */
    INVENTORY_MANAGE("can_manage_inventory", "库存管理"),
    /**
     * Represents the permission for managing income within the system.
     * This permission is associated with the database field "can_manage_income" and
     * has a human-readable description of "收入管理".
     */
    INCOME_MANAGE("can_manage_income", "收入管理");

    private final String dbField;
    private final String description;

    Permission(String dbField, String description) {
        this.dbField = dbField;
        this.description = description;
    }

    public String getDbField() {
        return dbField;
    }

    public String getDescription() {
        return description;
    }
}
