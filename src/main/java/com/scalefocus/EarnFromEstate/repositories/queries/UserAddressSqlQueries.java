package com.scalefocus.EarnFromEstate.repositories.queries;

public final class UserAddressSqlQueries {

    public static final String GET_USER_ADDRESS_BY_ALL_FIELDS = "SELECT * FROM USER_ADDRESS WHERE COUNTRY = :country " +
            "AND POST_CODE = :postCode AND CITY = :city AND STREET = :street AND STREET_NUMBER = :streetNumber";

    public static final String INSERT_USER_ADDRESS = "INSERT INTO USER_ADDRESS (CITY, COUNTRY, POST_CODE, STREET, " +
            "STREET_NUMBER) VALUES (:city, :country, :postCode, :street, :streetNumber)";
}
