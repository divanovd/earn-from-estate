package com.scalefocus.EarnFromEstate.repositories.role;

import com.scalefocus.EarnFromEstate.entities.Role;
import com.scalefocus.EarnFromEstate.exceptions.UserException;
import com.scalefocus.EarnFromEstate.repositories.queries.RoleSqlQueries;
import com.scalefocus.EarnFromEstate.repositories.rowmappers.RoleRowMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role getRoleByName(final String name) {
        Role role = null;
        try {
            final SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
            role = jdbcTemplate.queryForObject(RoleSqlQueries.GET_ROLE_BY_NAME,
                    parameterSource, new RoleRowMapper());
        } catch (final DataAccessException ex) {
            log.error("In getRoleByName(), failed to fetch role by name: {}", name);
            throw new UserException(ex.getMessage());
        }
        return role;
    }

    @Override
    public Set<Role> getRolesByUserId(final Long userId) {
        Set<Role> roles = null;
        try {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("id", userId);
            roles = new HashSet<>(jdbcTemplate.query(RoleSqlQueries.GET_ROLES_BY_USER_ID, parameterSource, new RoleRowMapper()));
        } catch (final DataAccessException ex) {
            log.error("In getRolesByUserId(), failed to fetch roles by userId: {}", userId);
            throw new UserException(ex.getMessage());
        }
        return roles;
    }

    @Override
    public Role saveRole(final Role role) {
        Role persistedRole = null;
        try {
            final SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(role);
            persistedRole = jdbcTemplate.queryForObject(RoleSqlQueries.GET_ROLE_BY_NAME,
                    parameterSource, new RoleRowMapper());
        } catch (final DataAccessException ex) {
            log.error("In getRoleByName(), failed to fetch role by name: {}", role.getName());
            throw new UserException(ex.getMessage());
        }
        return role;
    }
}
