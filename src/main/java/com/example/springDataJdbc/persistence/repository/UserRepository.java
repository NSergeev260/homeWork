package com.example.springDataJdbc.persistence.repository;

import com.example.springDataJdbc.persistence.model.UserData;

import java.util.Optional;

public interface UserRepository {

    Optional<UserData> findByEmail(String email);
}
