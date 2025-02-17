package com.security.model.dto;

import java.time.LocalDate;

public record ReadUserDto(
        String firstname,
        String lastname,
        LocalDate birthdate,
        String username
) {}
