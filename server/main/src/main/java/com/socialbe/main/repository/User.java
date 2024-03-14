package com.socialbe.main.repository;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "socialUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
