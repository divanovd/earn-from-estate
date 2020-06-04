package com.scalefocus.EarnFromEstate.repositories.rowmappers;

import com.scalefocus.EarnFromEstate.entities.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        return Role.builder()
                .id(resultSet.getLong("ID"))
                .name(resultSet.getString("NAME"))
                .build();
    }
}
