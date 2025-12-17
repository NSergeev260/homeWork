package com.example.springDataJdbc.persistence.repository;

import com.example.springDataJdbc.persistence.model.UserData;
import com.example.springDataJdbc.persistence.model.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<UserData> {

    @Override
    public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserData userData = new UserData();
        userData.setId(Long.valueOf(rs.getString("id")));
        userData.setName(rs.getString("name"));
        userData.setEmail(rs.getString("email"));
        userData.setProvider(rs.getString("provider"));
        userData.setProviderId(rs.getString("providerId"));
        userData.setRole(UserRole.valueOf(rs.getString("USER")));
        return userData;
    }
}

