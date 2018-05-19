package org.springframework.gsspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Table (name = "user")
@Entity
public class User {

    @Id
    @Column (name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, name="email")
    private String email;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="password")
    private String password;

    @Column(nullable = false, name = "customer_address")
    private String customerAddress;

    @Column(nullable = false, name = "customer_name")
    private String customerName;

    @Column(nullable = false, name = "customer_last_name")
    private String customerLastName;

    @Column (nullable = false, name = "customer_phone")
    private String customerPhone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Cart>carts = new HashSet<>();

}
