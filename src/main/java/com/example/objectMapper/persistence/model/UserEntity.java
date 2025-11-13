package com.example.objectMapper.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter
    @Setter
    private Long id;


    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column
    @Getter
    @Setter
    private UUID uuid;

    @Column(length = 60, nullable = false)
    @Setter
    @Getter
    private String email;

    @Column(length = 72, nullable = false)
    @Setter
    @Getter
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Setter
    @Getter
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private UserRole role;

    @PrePersist
    public void prePersist() {
        uuid = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(id, user.id) && Objects.equals(uuid, user.uuid) &&
                Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, email, password);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
