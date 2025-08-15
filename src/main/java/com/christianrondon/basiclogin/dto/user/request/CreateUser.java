package com.christianrondon.basiclogin.dto.user.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
    @NotBlank(message = "is required")
    private String name;

    @NotBlank(message = "is required")
    @Email(message = "email is not on format")
    private String email;

    @NotBlank(message = "is required")
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;


}
