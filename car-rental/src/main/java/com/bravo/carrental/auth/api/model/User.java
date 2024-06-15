package com.bravo.carrental.auth.api.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

}
