package com.mindden.enums;

public enum RoleType {
	
	ADMIN, USER;

    RoleType() {}

    public static boolean isUser(final RoleType role) {
        return USER.equals(role);
    }

    public static boolean isAdmin(final RoleType role) {
        return ADMIN.equals(role);
    }

}
