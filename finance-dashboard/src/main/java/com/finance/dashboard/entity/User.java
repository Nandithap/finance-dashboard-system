package com.finance.dashboard.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    // Default status for new users can be "ACTIVE"
    private String status = "ACTIVE";

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Hide records in JSON to avoid null issues and infinite recursion
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FinancialRecord> records;

    public User() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<FinancialRecord> getRecords() { return records; }
    public void setRecords(List<FinancialRecord> records) { this.records = records; }
}