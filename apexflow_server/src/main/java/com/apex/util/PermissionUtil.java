package com.apex.util;

import com.apex.core.model.SystemUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Utility class for managing and checking user permissions.
 */
public class PermissionUtil {

    /**
     * Checks if the specified user has the given permission.
     * If the user is an admin, it automatically returns true.
     *
     * @param user       The SystemUser object to check for permissions.
     * @param permission The Permission object to verify against the user's permissions.
     * @return true if the user has the specified permission or is an admin, false otherwise.
     */
    public static boolean hasPermission(SystemUser user, Permission permission) {
        if (user == null) return false;

        if (Boolean.TRUE.equals(user.getAdmin())) {
            return true;
        }

        return switch (permission) {
            case ORDER_MANAGE -> Boolean.TRUE.equals(user.getCanManageOrder());
            case LOGISTICS_MANAGE -> Boolean.TRUE.equals(user.getCanManageLogistics());
            case AFTER_SALES_MANAGE -> Boolean.TRUE.equals(user.getCanManageAfterSales());
            case REVIEW_MANAGE -> Boolean.TRUE.equals(user.getCanManageReview());
            case INVENTORY_MANAGE -> Boolean.TRUE.equals(user.getCanManageInventory());
            case INCOME_MANAGE -> Boolean.TRUE.equals(user.getCanManageIncome());
            case ADMIN -> Boolean.TRUE.equals(user.getAdmin());
        };
    }


    /**
     * Determines if the specified user has at least one of the given permissions.
     * If the user is an admin, it automatically returns true.
     *
     * @param user        The SystemUser object to check for permissions.
     * @param permissions A variable number of Permission objects to verify against the user's permissions.
     * @return true if the user has any of the specified permissions or is an admin, false otherwise.
     */
    public static boolean hasAnyPermission(SystemUser user, Permission... permissions) {
        if (user == null) return false;

        if (Boolean.TRUE.equals(user.getAdmin())) {
            return true;
        }

        for (Permission permission : permissions) {
            if (hasPermission(user, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the specified user has all the given permissions.
     *
     * @param user        The SystemUser object to check for permissions.
     * @param permissions A variable number of Permission objects to verify against the user's permissions.
     * @return true if the user has all the specified permissions, false otherwise.
     */
    public static boolean hasAllPermissions(SystemUser user, Permission... permissions) {
        if (user == null) return false;

        if (Boolean.TRUE.equals(user.getAdmin())) {
            return true;
        }

        for (Permission permission : permissions) {
            if (!hasPermission(user, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves a list of permissions that the specified user has.
     *
     * @param user The SystemUser object for which to retrieve permissions.
     * @return A List containing Permission objects representing the user's permissions.
     */
    public static List<Permission> getUserPermissions(SystemUser user) {
        List<Permission> permissions = new ArrayList<>();

        if (user == null) return permissions;

        if (Boolean.TRUE.equals(user.getAdmin())) {
            permissions.add(Permission.ADMIN);
        }

        if (Boolean.TRUE.equals(user.getCanManageOrder())) {
            permissions.add(Permission.ORDER_MANAGE);
        }
        if (Boolean.TRUE.equals(user.getCanManageLogistics())) {
            permissions.add(Permission.LOGISTICS_MANAGE);
        }
        if (Boolean.TRUE.equals(user.getCanManageAfterSales())) {
            permissions.add(Permission.AFTER_SALES_MANAGE);
        }
        if (Boolean.TRUE.equals(user.getCanManageReview())) {
            permissions.add(Permission.REVIEW_MANAGE);
        }
        if (Boolean.TRUE.equals(user.getCanManageInventory())) {
            permissions.add(Permission.INVENTORY_MANAGE);
        }
        if (Boolean.TRUE.equals(user.getCanManageIncome())) {
            permissions.add(Permission.INCOME_MANAGE);
        }

        return permissions;
    }
}
