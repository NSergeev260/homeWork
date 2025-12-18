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
import java.util.UUID;


@Slf4j
@Repository
@AllArgsConstructor
public class UserRepositoryJdbc implements UserRepository {

    private static final String INSERT_USER =
            "INSERT INTO users(id, name, email, provider, providerId, role) " +
                    "VALUES (:id, :name, :email, :provider, :providerId, :role)";

    private static final String GET_USER_BY_ID =
            "SELECT * FROM users WHERE id = :id";

    private static final String GET_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email = :email";

    private static final String UPDATE_USER =
            "UPDATE users SET name = :name, email = :email, provider = :provider, " +
                    "providerId = :providerId, role = :role WHERE id = :id";

    private static final String DELETE_USER =
            "DELETE FROM users WHERE id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public UserData insertUser(UserData userData) {
        UUID newId = UUID.randomUUID();
        SqlParameterSource insertParameters = new MapSqlParameterSource()
                .addValue("id", newId)
                .addValue("name", userData.getName())
                .addValue("email", userData.getEmail())
                .addValue("provider", userData.getProvider())
                .addValue("providerId", userData.getProviderId())
                .addValue("role", userData.getRole());
        namedParameterJdbcTemplate.update(INSERT_USER, insertParameters);
        userData.setId(newId);

        return userData;
    }

    @Override
    public Optional<UserData> findUserById(UUID id) {
        try {
            SqlParameterSource findParameters = new MapSqlParameterSource()
                    .addValue("id", id);
            UserData userData = namedParameterJdbcTemplate.queryForObject(
                    GET_USER_BY_ID, findParameters, userRowMapper);

            return Optional.ofNullable(userData);

        } catch (EmptyResultDataAccessException e) {
            log.debug("User id not found. FAIL! id: {}", id);

            return Optional.empty();
        }
    }

    public Optional<UserData> findByEmail(String email) {
        try {
            SqlParameterSource findParameters = new MapSqlParameterSource()
                    .addValue("email", email);
            UserData userData = namedParameterJdbcTemplate.queryForObject(
                    GET_USER_BY_EMAIL, findParameters, userRowMapper);

            return Optional.ofNullable(userData);

        } catch (EmptyResultDataAccessException e) {
            log.debug("Email not found. FAIL! email: {}", email);

            return Optional.empty();
        }
    }

    @Override
    public UserData updateUser(UserData userData) {
        SqlParameterSource updateParameters = new MapSqlParameterSource()
                .addValue("name", userData.getName())
                .addValue("email", userData.getEmail())
                .addValue("provider", userData.getProvider())
                .addValue("providerId", userData.getProviderId())
                .addValue("role", userData.getRole());
        namedParameterJdbcTemplate.update(UPDATE_USER, updateParameters);

        return userData;
    }

    @Override
    public void deleteUser(UUID id) {
        SqlParameterSource deleteParameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_USER, deleteParameters);
    }
}
