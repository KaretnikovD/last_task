package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;


@Entity
@Table(name = "roles_new")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Role implements GrantedAuthority {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getReadableName() {
        return getName().replaceAll("ROLE_", "");
    }


}

