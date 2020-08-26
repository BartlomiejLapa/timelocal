package com.timeactuall.demo.security.dto;

import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {

        @NotBlank(message = "Username is required")
        @Size(min = 4, message = "Username must be at least 3 characters")
        private String username;

        @NotEmpty
        private String password;

        @NotEmpty
        @Transient
        private String passwordConfirm;
}
