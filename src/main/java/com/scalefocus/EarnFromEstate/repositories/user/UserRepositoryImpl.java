package com.scalefocus.EarnFromEstate.repositories.user;

import com.scalefocus.EarnFromEstate.entities.User;
import com.scalefocus.EarnFromEstate.exceptions.UserException;
import com.scalefocus.EarnFromEstate.repositories.queries.UserSqlQueries;
import com.scalefocus.EarnFromEstate.repositories.rowmappers.UserRowMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Data
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String registerUser(final User user) {
        int updatedRows = 0;
        try {
            final SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
            updatedRows = jdbcTemplate.update(UserSqlQueries.REGISTER_USER,
                    parameterSource);
        } catch (final DataAccessException ex) {
            log.error("In registerUser(), failed to persist user: {}", user);
            throw new UserException(ex.getMessage());
        }

        log.info("In registeUser(), successfully registered user: {}.", user);
        return user.getEmail();
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        Optional<User> existingUser = Optional.empty();
        try {
            final SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("email", email);
            final User user = jdbcTemplate.queryForObject(UserSqlQueries.GET_USER_BY_EMAIL,
                    parameterSource, new UserRowMapper());
            existingUser = Optional.ofNullable(user);
            log.info("In getUserByEmail(), successfully fetched user with email: {}", email);

        } catch (final EmptyResultDataAccessException ex) {
            log.info("In getUserByEmail(), there is no user with email: {}, registered.", email);

        } catch (final DataAccessException ex) {
            log.error("In getUserByEmail(), failed to find user with email: {}", email);
            throw new UserException(ex.getMessage());
        }

        return existingUser;
    }

    @Override
    public int insertUserRoleRelationship(final Long userId, final Long roleId) {
        int updatedRows = 0;
        try {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("userId", userId).addValue("roleId", roleId);
            updatedRows = jdbcTemplate.update(UserSqlQueries.INSERT_USER_ROLES_RELATIONSHIP,
                    parameterSource);
        } catch (final DataAccessException ex) {
            log.error("In insertUserRoleRelationship(), failed to insert relationship for userId: {}" +
                    "and roleId: {}", userId, roleId);
            throw new UserException(ex.getMessage());
        }
        log.info("In insertUserRoleRelationship(), successfully, inserted relationship for userId: {}" +
                "and roleId: {}", userId, roleId);

        return updatedRows;
    }

}
