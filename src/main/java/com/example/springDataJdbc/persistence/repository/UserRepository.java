package com.example.springDataJdbc.persistence.repository;

import com.example.springDataJdbc.persistence.model.BookData;
import com.example.springDataJdbc.persistence.model.UserData;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    UserData insertUser(UserData userData);

    Optional<UserData> findUserById(UUID id);

    Optional<UserData> findByEmail(String email);

    UserData updateUser(UserData userData);

    void deleteUser(UUID id);
}
