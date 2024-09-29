package com.security.model.dto;

import java.time.LocalDate;

public record EditUserDto(
        String firstname,
        String lastname,
        LocalDate birthdate,
        String username,
        String password
) {}
