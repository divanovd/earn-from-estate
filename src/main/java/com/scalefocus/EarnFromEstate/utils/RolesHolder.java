package com.scalefocus.EarnFromEstate.utils;

/**
 * Used for holding the roles.
 */
public enum RolesHolder {

    ADMIN(Value.ROLE_ADMIN),
    TENANT(Value.ROLE_TENANT),
    LANDLORD(Value.ROLE_LANDLORD);

    private final String value;

    private RolesHolder(final String value) {
        this.value = value;
    }

    private static class Value {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_TENANT = "ROLE_TENANT";
        public static final String ROLE_LANDLORD = "ROLE_LANDLORD";
    }
}
