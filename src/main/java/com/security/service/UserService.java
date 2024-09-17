package com.security.service;

import com.security.model.dto.CreateUserDto;
import com.security.model.dto.EditUserDto;
import com.security.model.dto.ReadUserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ReadUserDto create(CreateUserDto user);

    Optional<ReadUserDto> findById(Long id);

    List<ReadUserDto> findAll();

    Optional<ReadUserDto> update(Long id, EditUserDto user);

    boolean delete(Long id);
}
