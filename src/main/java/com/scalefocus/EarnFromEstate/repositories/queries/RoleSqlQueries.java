package com.scalefocus.EarnFromEstate.repositories.queries;

public final class RoleSqlQueries {

    public static final String GET_ROLE_BY_NAME = "SELECT * FROM ROLES WHERE name = :name";
    public static final String SAVE_ROLE = "INSERT INTO ROLES (name) FROM ROLES WHERE name = :name";
}
