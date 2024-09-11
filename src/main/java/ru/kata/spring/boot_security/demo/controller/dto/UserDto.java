package ru.kata.spring.boot_security.demo.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import org.springframework.lang.NonNull;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

public class UserDto {


    private Long id;
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @Min(1)
    private Integer age;


    private String password;

    private Collection<Role> roles;

    @Email
    @NonNull

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
