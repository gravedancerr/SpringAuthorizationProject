package com.authorizationsystem.model;

import com.authorizationsystem.dto.EditUserDto;
import com.authorizationsystem.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    @NotNull
    private String login;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "birthday")
    @NotNull
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(UserDto userDto) {
        BeanUtils.copyProperties(userDto, this);
    }

    public User(EditUserDto editUserDto) {
        BeanUtils.copyProperties(editUserDto, this);
    }
}
