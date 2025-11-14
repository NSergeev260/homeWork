package com.example.objectMapper.security.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

@Getter
public class ObjMapperUserDetails extends User {
    private final UUID id;

    public ObjMapperUserDetails(
            UUID id, String username, String password, Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
        this.id = id;
    }
}


