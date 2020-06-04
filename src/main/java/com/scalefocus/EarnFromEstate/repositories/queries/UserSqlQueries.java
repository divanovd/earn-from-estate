package com.scalefocus.EarnFromEstate.repositories.queries;

public final class UserSqlQueries {

    public static final String REGISTER_USER = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL, " +
            "PASSWORD, MATCHING_PASSWORD, MOBILE_PHONE, IS_ENABLED, ADDRESS_ID) " +
            "VALUES (:firstName, :lastName, :email, :password, :matchingPassword, :mobilePhone," +
            " :isEnabled, :userAddress.id)";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM USERS WHERE EMAIL = :email";

    public static final String INSERT_USER_ROLES_RELATIONSHIP = "INSERT INTO USER_ROLES (USER_ID, ROLE_ID)" +
            "VALUES (:userId, :roleId)";

}
