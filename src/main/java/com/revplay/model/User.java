package com.revplay.model;

import com.revplay.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String passwordHint;

    @Enumerated(EnumType.STRING)
    private Role role;

    // getters & setters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPasswordHint() { return passwordHint; }
    public Role getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPasswordHint(String passwordHint) { this.passwordHint = passwordHint; }
    public void setRole(Role role) { this.role = role; }
}
