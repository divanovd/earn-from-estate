package com.scalefocus.EarnFromEstate.repositories.rowmappers;

import com.scalefocus.EarnFromEstate.entities.User;
import com.scalefocus.EarnFromEstate.entities.UserAddress;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        return User.builder()
                .Id(resultSet.getLong("ID"))
                .firstName(resultSet.getString("FIRST_NAME"))
                .lastName(resultSet.getString("LAST_NAME"))
                .email(resultSet.getString("EMAIL"))
                .password(resultSet.getString("PASSWORD"))
                .mobilePhone(resultSet.getString("MOBILE_PHONE"))
                .isEnabled(resultSet.getString("IS_ENABLED"))
                .userAddress(UserAddress.builder().id(resultSet.getLong("ADDRESS_ID")).build())
                .build();
    }
}
