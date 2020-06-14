package com.scalefocus.EarnFromEstate.repositories.userАddress;

import com.scalefocus.EarnFromEstate.entities.UserAddress;
import com.scalefocus.EarnFromEstate.exceptions.UserException;
import com.scalefocus.EarnFromEstate.repositories.queries.UserAddressSqlQueries;
import com.scalefocus.EarnFromEstate.repositories.rowmappers.UserAddressRowMapper;
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

@Data
@Slf4j
@Repository
public class UserAddressRepositoryImpl implements UserAddressRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserAddressRepositoryImpl(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Gets the user's address by all the fields in order to obtain the whole object including the id.
     *
     * @param userAddress user address.
     * @return the user's address with id.
     */
    @Override
    public UserAddress getUserAddressByAllFields(final UserAddress userAddress) {
        UserAddress registeredAddress = null;
        try {
            final SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAddress);

            registeredAddress = jdbcTemplate.queryForObject(UserAddressSqlQueries.GET_USER_ADDRESS_BY_ALL_FIELDS,
                    parameterSource, new UserAddressRowMapper());
        } catch (final EmptyResultDataAccessException ex) {
            log.info("In getUserAddressById(), there is no userAddress: {}.", userAddress);
            return null;
        } catch (final DataAccessException ex) {
            log.error("In getUserAddressById(), failed to find userAddress: {}", userAddress);
            throw new UserException(ex.getMessage());
        }

        log.info("In getUserAddressById(), successfully fetched userAddress: {}", userAddress);
        return registeredAddress;
    }

    /**
     * Gets the userAddress by given id.
     *
     * @param id the id of the address.
     * @return the UserAdddress object.
     */
    @Override
    public UserAddress getUserAddressById(final Long id) {
        UserAddress userAddress = null;
        try {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("id", id);
            userAddress = jdbcTemplate.queryForObject(UserAddressSqlQueries.GET_USER_ADDRESS_BY_USER_ID,
                    parameterSource, new UserAddressRowMapper());
        } catch (final DataAccessException ex) {
            log.error("In getUserAddressById(), failed to fetch userAddress by id: {}", id);
            throw new UserException(ex.getMessage());
        }
        return userAddress;
    }

    /**
     * Persists the user address in the dbs.
     *
     * @param userAddress the user address
     * @return the rows that have been changed.
     */
    @Override
    public int saveUserAddress(final UserAddress userAddress) {
        int updatedRows = 0;
        try {
            final SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userAddress);
            updatedRows = jdbcTemplate.update(UserAddressSqlQueries.INSERT_USER_ADDRESS,
                    parameterSource);
        } catch (final DataAccessException ex) {
            log.error("In saveUserAddres(), failed to persist address: {}", userAddress);
            throw new UserException(ex.getMessage());
        }
        log.info("In saveUserAddres(), successfully registered address: {}.", userAddress);

        return updatedRows;
    }

}
