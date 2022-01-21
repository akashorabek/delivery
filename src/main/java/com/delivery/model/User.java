package com.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String email;

    @Column(length = 200)
    private String password;

    private boolean enabled;

    @Column(length = 30)
    private String role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        enabled = true;
        role = "USER";
    }
}
