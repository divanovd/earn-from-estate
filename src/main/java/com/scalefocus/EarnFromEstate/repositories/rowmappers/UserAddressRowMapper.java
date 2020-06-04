package com.scalefocus.EarnFromEstate.repositories.rowmappers;

import com.scalefocus.EarnFromEstate.entities.UserAddress;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressRowMapper implements RowMapper<UserAddress> {

    @Override
    public UserAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserAddress.builder()
                .id(rs.getLong("ID"))
                .country(rs.getString("COUNTRY"))
                .postCode(rs.getInt("POST_CODE"))
                .city(rs.getString("CITY"))
                .street(rs.getString("STREET"))
                .streetNumber(rs.getString("STREET_NUMBER"))
                .build();
    }
}
