package com.authorizationsystem.dto;

import com.authorizationsystem.model.Role;
import com.authorizationsystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {
    private Long id;

    @Size(min = 4, max = 70)
    private String login;

    @Size(max = 70)
    private String password;

    @Size(max = 70)
    private String email;

    @Size(min = 1, max = 70)
    private String firstName;

    @Size(min = 1, max = 70)
    private String lastName;

    @Past
    private Date birthday;

    private Role role;
}
