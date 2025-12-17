package com.example.springDataJdbc.persistence.repository;

import com.example.springDataJdbc.persistence.model.UserData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Slf4j
@Repository
@AllArgsConstructor
public class UserRepositoryJdbc implements UserRepository{

    private static final String GET_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email = :email";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    public Optional<UserData> findByEmail(String email) {
        try {
            SqlParameterSource findParameters = new MapSqlParameterSource()
                    .addValue("email", email);
            UserData userData = namedParameterJdbcTemplate.queryForObject(
                    GET_USER_BY_EMAIL, findParameters, userRowMapper);

            return Optional.ofNullable(userData);

        } catch (EmptyResultDataAccessException e) {
            log.debug("User not found. FAIL! email: {}", email);

            return Optional.empty();
        }
    }
}
