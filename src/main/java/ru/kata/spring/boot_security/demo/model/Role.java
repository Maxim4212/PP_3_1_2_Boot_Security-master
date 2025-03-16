package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role")
    private String user_role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    public Role() {
    }

    public Role(String user_role) {
        this.user_role = user_role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + user_role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return user_role;
    }

    public void setName(String user_role) {
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return user_role;
    }
}
