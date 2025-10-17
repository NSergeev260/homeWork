package com.example.jsonView.persistence.model;

import com.example.jsonView.usercasses.dto.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String userName;

    @Column(name = "surname", nullable = false)
    private String userSurname;

    @Column(name= "email", nullable = false)
    private String userEmail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orderList;
}
